import { Component, OnInit, Inject, ViewEncapsulation } from '@angular/core';
import { fuseAnimations } from '@fuse/animations';
import { FormGroup, FormBuilder } from '@angular/forms';
import { UserModel } from '../../../../model/user.model';
import { UserService } from '../user.service';
import { MatDialogRef } from '@angular/material';

@Component({
    selector: 'fuse-user-update-dialog',
    templateUrl: './userUpdate.component.html',
    styleUrls: ['./userUpdate.component.scss'],
    animations: fuseAnimations,
    encapsulation: ViewEncapsulation.None
})
export class UserUpdateDialogComponent implements OnInit 
{
    dialogTitle = 'Add/ Edit User';

    userUpdateForm: FormGroup;

    constructor(private _formBuilder: FormBuilder, private _userService: UserService, 
            private _dialogRef: MatDialogRef<UserUpdateDialogComponent>)
    {
        this.userUpdateForm = _formBuilder.group({
            firstname   : [''],
            lastname    : [''],
            email       : [''],
            password    : [''],
            sapBPNumber : [''],
            role        : ['ZLM013']
        });
    }

    ngOnInit(): void 
    {
    }

    submit(): void 
    {
        if (this.userUpdateForm.valid) 
        {
            const user: UserModel = new UserModel(this.userUpdateForm.value);
            this._userService.createUser(user).subscribe(() => {
                this._dialogRef.close();
            });
        }
    }
}
