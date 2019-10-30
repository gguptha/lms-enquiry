import { Component, OnDestroy, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';

import { FuseConfigService } from '@fuse/services/config.service';
import { fuseAnimations } from '@fuse/animations';
import { UserModel } from '../../model/user.model';
import { RegisterService } from './register.service';
import {MatSnackBar} from "@angular/material";
import {AppService} from "../../../../app.service";

@Component({
    selector   : 'register-2',
    templateUrl: './register.component.html',
    styleUrls  : ['./register.component.scss'],
    animations : fuseAnimations
})
export class Register2Component implements OnInit, OnDestroy
{
    registerForm: FormGroup;

    displayForm = true;

    userEmail: string;

    termsAndConditionsCheck: boolean = false;


    // Private
    private _unsubscribeAll: Subject<any>;

    constructor(
        private _fuseConfigService: FuseConfigService,
        private _formBuilder: FormBuilder,
        private _registerService: RegisterService,
        private _matSnackBar: MatSnackBar,
        private _appService: AppService
    )
    {
        // Configure the layout
        this._fuseConfigService.config = {
            layout: {
                navbar   : {
                    hidden: true
                },
                toolbar  : {
                    hidden: true
                },
                footer   : {
                    hidden: true
                },
                sidepanel: {
                    hidden: true
                }
            }
        };

        // Set the private defaults
        this._unsubscribeAll = new Subject();
    }

    // -----------------------------------------------------------------------------------------------------
    // @ Lifecycle hooks
    // -----------------------------------------------------------------------------------------------------

    /**
     * On init
     */
    ngOnInit(): void
    {
        this.registerForm = this._formBuilder.group({
            firstname       : ['', Validators.required],
            lastname        : ['', Validators.required],
            email           : ['', [Validators.required, Validators.pattern(/^[\w\-\.\+]+\@[a-zA-Z0-9\.\-]+\.[a-zA-z0-9]{2,4}$/)]],
            //password        : ['', [Validators.required, Validators.pattern(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/)]],
            password        : ['', [Validators.required, Validators.pattern(/^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/)]],
           passwordConfirm : ['', [Validators.required, confirmPasswordValidator]],
          termsAndConditionsCheck:['']
        });

        // Update the validity of the 'passwordConfirm' field
        // when the 'password' field changes
        this.registerForm.get('password').valueChanges
            .pipe(takeUntil(this._unsubscribeAll))
            .subscribe(() => {
                //this.registerForm.get('passwordConfirm').updateValueAndValidity();

                if (this.termsAndConditionsCheck === true){
                  this.registerForm.get('passwordConfirm').updateValueAndValidity();
                }
            });
    }
  /**
   * Terms and Conditions Check
   */
    termsAndConditions(values:any){

      this.termsAndConditionsCheck = !this.termsAndConditionsCheck;

  }


    /**
     * On destroy
     */
    ngOnDestroy(): void
    {
        // Unsubscribe from all subscriptions
        this._unsubscribeAll.next();
        this._unsubscribeAll.complete();
    }

    /**
     * register()
     */
    register(): void {

      if (this.termsAndConditionsCheck === false ){
        this._matSnackBar.open('Accept the Terms & Conditions to sign up', 'OK', { duration: 3000 });

        return;
      }

      let firstNameLowerCase = this.registerForm.value.firstname.toLowerCase();
      let lastNameLowerCase = this.registerForm.value.lastname.toLowerCase();


      //Additional Password Validations
      if (this.registerForm.value.password.includes(this.registerForm.value.firstname) ||
          this.registerForm.value.password.includes(firstNameLowerCase)) {
          this._matSnackBar.open('Error: Password should not contain first name of the user.', 'OK', {
            duration: 5000
          });
          return;
      }


      if (this.registerForm.value.password.includes(this.registerForm.value.lastname)||
          this.registerForm.value.password.includes(lastNameLowerCase )) {
        this._matSnackBar.open('Error: Password should not contain last name of the user.', 'OK', {
          duration: 5000
        });
        return;
      }

      if (this.registerForm.value.password.includes('123') ||
          this.registerForm.value.password.includes('1234') ||
          this.registerForm.value.password.includes('12345')) {
        this._matSnackBar.open('Error: Password should not contain running numbers.', 'OK', {
          duration: 5000
        });
        return;
      }


      const user: UserModel = new UserModel(this.registerForm.value);
        this._registerService.register(user).subscribe((response) => {
            this.userEmail = this.registerForm.value.email;
            this.registerForm.reset();
            this.displayForm = false;
        },
        (error) => {
            this._matSnackBar.open('User already exists or other errors occured.', 'OK', { duration: 7000 });
        });
    }
}

/**
 * Confirm password validator
 *
 * @param {AbstractControl} control
 * @returns {ValidationErrors | null}
 */
export const confirmPasswordValidator: ValidatorFn = (control: AbstractControl): ValidationErrors | null => {

    if ( !control.parent || !control )
    {
        return null;
    }

    const password = control.parent.get('password');
    const passwordConfirm = control.parent.get('passwordConfirm');

    if ( !password || !passwordConfirm )
    {
        return null;
    }

    if ( passwordConfirm.value === '' )
    {
        return null;
    }

    if ( password.value === passwordConfirm.value )
    {
        return null;
    }

    return { 'passwordsNotMatching': true };
};
