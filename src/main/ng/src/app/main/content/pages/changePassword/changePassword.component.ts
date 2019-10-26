import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { FormBuilder, FormGroup, Validators, AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';

import { FuseConfigService } from '@fuse/services/config.service';
import { fuseAnimations } from '@fuse/animations';
import { MatSnackBar } from '@angular/material';
import { ChangePasswordService } from './changePassword.service';
import { Router } from '@angular/router';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import {AppService} from "../../../../app.service";

@Component({
    selector: 'change-password',
    templateUrl: './changePassword.component.html',
    styleUrls: ['./changePassword.component.scss'],
    encapsulation: ViewEncapsulation.None,
    animations: fuseAnimations
})
export class ChangePasswordComponent implements OnInit {
    forgotPasswordForm: FormGroup;

    _unsubscribeAll: Subject<any>;

    /**
     * Constructor
     *
     * @param {FuseConfigService} _fuseConfigService
     * @param {FormBuilder} _formBuilder
     */
    constructor(
        private _fuseConfigService: FuseConfigService,
        private _formBuilder: FormBuilder, private _changePasswordService: ChangePasswordService,
        private _matSnackBar: MatSnackBar, private _router: Router,
        private _appService: AppService
    ) {
        // Configure the layout
        this._fuseConfigService.config = {
            layout: {
                navbar: {
                    hidden: true
                },
                toolbar: {
                    hidden: true
                },
                footer: {
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
    ngOnInit(): void {
        this.forgotPasswordForm = this._formBuilder.group({
            password        : ['', [Validators.required, Validators.pattern(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/)]],
            passwordConfirm : ['', [Validators.required, confirmPasswordValidator]]
        });




        // Update the validity of the 'passwordConfirm' field
        // when the 'password' field changes
        this.forgotPasswordForm.get('password').valueChanges
            .pipe(takeUntil(this._unsubscribeAll))
            .subscribe(() => {
                this.forgotPasswordForm.get('passwordConfirm').updateValueAndValidity();
            });
    }

    /**
     * submit()
     */
    submit(): void {

      let firstNameLowerCase = this._appService.currentUser.firstName.toLowerCase;
      let lastNameLowerCase = this._appService.currentUser.lastName.toLowerCase();
      let firstName = this._appService.currentUser.firstName;
      let lastName = this._appService.currentUser.lastName;


      let password = this.forgotPasswordForm.value.password;

      //Additional Password Validations
      if (password.includes(firstName) ||
          password.includes(firstNameLowerCase)) {
        this._matSnackBar.open('Error: Password should not contain first name of the user.', 'OK', {
          duration: 5000
        });
        return;
      }


      if (password.includes(lastName)||
          password.includes(lastNameLowerCase )) {
        this._matSnackBar.open('Error: Password should not contain last name of the user.', 'OK', {
          duration: 5000
        });
        return;
      }

      if (password.includes('123') ||
        password.includes('1234') ||
        password.includes('12345')) {
        this._matSnackBar.open('Error: Password should not contain running numbers.', 'OK', {
          duration: 5000
        });
        return;
      }

        this._changePasswordService.changePassword(this.forgotPasswordForm.value.password).subscribe(response => {
            this._matSnackBar.open('Password is updated.', 'OK', {
                duration: 7000
            });
            this._router.navigate(['enquiryApplication']);
        },
        (error: string) => {
            // Show a snack.
            if (error.startsWith('status 412 reading OAuthClient#modifyPassword')) {
                this._matSnackBar.open('Password cannot be the same as the previous 3 passwords', 'OK', { duration: 7000 });
            }
            else {
                this._matSnackBar.open(error, 'OK', { duration: 7000 });
            }
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
