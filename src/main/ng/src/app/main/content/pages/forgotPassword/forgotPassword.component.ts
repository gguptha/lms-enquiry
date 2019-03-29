import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { FuseConfigService } from '@fuse/services/config.service';
import { fuseAnimations } from '@fuse/animations';
import { ForgotPasswordService } from './forgotPassword.service';
import { MatSnackBar } from '@angular/material';

@Component({
    selector: 'forgot-password-2',
    templateUrl: './forgotPassword.component.html',
    styleUrls: ['./forgotPassword.component.scss'],
    encapsulation: ViewEncapsulation.None,
    animations: fuseAnimations
})
export class ForgotPassword2Component implements OnInit {
    forgotPasswordForm: FormGroup;

    /**
     * Constructor
     *
     * @param {FuseConfigService} _fuseConfigService
     * @param {FormBuilder} _formBuilder
     */
    constructor(
        private _fuseConfigService: FuseConfigService,
        private _formBuilder: FormBuilder, private _forgotPasswordService: ForgotPasswordService,
        private _matSnackBar: MatSnackBar
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
    }

    // -----------------------------------------------------------------------------------------------------
    // @ Lifecycle hooks
    // -----------------------------------------------------------------------------------------------------

    /**
     * On init
     */
    ngOnInit(): void {
        this.forgotPasswordForm = this._formBuilder.group({
            emailId: ['', [Validators.required, Validators.email]]
        });
    }

    /**
     * submit()
     */
    submit(): void {
        console.log(this.forgotPasswordForm.value);
        this._matSnackBar.open('Generating a new password. Please wait.', 'OK', {
            duration: 10000
        });
        this._forgotPasswordService.resetPassword(this.forgotPasswordForm.value.emailId).subscribe(response => {
            this._matSnackBar.open('New password is sent to the email address provided.', 'OK', {
                duration: 7000
            });
            this.forgotPasswordForm.reset();
        });
    }
}
