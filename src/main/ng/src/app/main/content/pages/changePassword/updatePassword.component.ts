import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { fuseAnimations } from '@fuse/animations';
import { FormGroup, FormBuilder, Validators, ValidatorFn, AbstractControl, ValidationErrors } from '@angular/forms';
import { MatSnackBar, MatDialogRef } from '@angular/material';
import { ChangePasswordService } from './changePassword.service';
import { Router } from '@angular/router';

@Component({
    selector: 'fuse-update-password',
    templateUrl: './updatePassword.component.html',
    styleUrls: ['./updatePassword.component.scss'],
    animations: fuseAnimations,
    encapsulation: ViewEncapsulation.None
})
export class UpdatePasswordComponent implements OnInit {

    dialogTitle = 'Change Password';

    updatePasswordForm: FormGroup;

    constructor(_formBuilder: FormBuilder, public _dialogRef: MatDialogRef<UpdatePasswordComponent>, private _passwordService: ChangePasswordService,
        private _matSnackBar: MatSnackBar) {

        this.updatePasswordForm = _formBuilder.group({
            password        : ['', [Validators.required, Validators.pattern(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/)]],
            passwordConfirm : ['', [Validators.required, confirmPasswordValidator]]
        });
    }

    ngOnInit(): void {
    }

    submit(): void {
        this._passwordService.changePassword(this.updatePasswordForm.value.password).subscribe(response => {
            this._matSnackBar.open('Password is updated.', 'OK', {
                duration: 7000
            });
            this._dialogRef.close();
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
