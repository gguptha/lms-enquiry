import { Component, OnInit, Inject, ViewEncapsulation } from '@angular/core';
import { fuseAnimations } from '@fuse/animations';
import { FormGroup, FormBuilder } from '@angular/forms';
import { UserModel } from '../../../../model/user.model';
import { UserService } from '../user.service';
import { MatDialogRef, MAT_DIALOG_DATA, MatSnackBar } from '@angular/material';

@Component({
    selector: 'fuse-user-update-dialog',
    templateUrl: './userUpdate.component.html',
    styleUrls: ['./userUpdate.component.scss'],
    animations: fuseAnimations,
    encapsulation: ViewEncapsulation.None
})
export class UserUpdateDialogComponent implements OnInit {

    dialogTitle = 'Add/ Edit User';

    selectedUser: UserModel;

    userUpdateForm: FormGroup;

    constructor(_formBuilder: FormBuilder, private _userService: UserService,
        public _dialogRef: MatDialogRef<UserUpdateDialogComponent>, @Inject(MAT_DIALOG_DATA) public _dialogData: any,
        private _matSnackBar: MatSnackBar) {

        // Fetch selected user details from the dialog's data attribute.
        if (_dialogData.selectedUser !== undefined) {
            this.selectedUser = _dialogData.selectedUser;
        }
        else {
            this.selectedUser = new UserModel({});
        }

        this.userUpdateForm = _formBuilder.group({
            firstname: [this.selectedUser.firstName || ''],
            lastname: [this.selectedUser.lastName || ''],
            email: [this.selectedUser.email || ''],
            password: [''],
            sapBPNumber: [this.selectedUser.sapBPNumber || ''],
            role: [this.selectedUser.role || 'ZLM013'],
            riskDepartment: [this.selectedUser.riskDepartment]
        });
    }

    ngOnInit(): void {
    }

    submit(): void {
        if (this.userUpdateForm.valid) {
            const user: UserModel = new UserModel(this.userUpdateForm.value);
            if (this._dialogData.operation === 'addUser') {
                this._userService.createUser(user).subscribe(() => {
                    // Refresh the user list.
                    this._userService.getUsers();
                    // Show a snack.
                    this._matSnackBar.open('New user is added successfully.', 'OK', { duration: 7000 });
                    // Close the add/ update dialog.
                    this._dialogRef.close();
                });
            }
            else {
                this._userService.updateUser(user).subscribe(() => {
                    // Refresh the user list.
                    this._userService.getUsers();
                    // Show a snack.
                    this._matSnackBar.open('User details are updated successfully.', 'OK', { duration: 7000 });
                    // Close the add/ update dialog.
                    this._dialogRef.close();
                });
            }
        }
    }
}
