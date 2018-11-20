import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { MatTableDataSource } from '@angular/material';
import { BehaviorSubject } from 'rxjs';
import { fuseAnimations } from '@fuse/animations';
import { UserModel } from '../../../../model/user.model';
import { UserService } from '../user.service';

@Component({
    selector: 'fuse-user-list',
    templateUrl: './userList.component.html',
    styleUrls: ['./userList.component.scss'],
    animations: fuseAnimations,
    encapsulation: ViewEncapsulation.None
})
export class UserListComponent implements OnInit {

    dataSource: UserDataSource;
    
    selectedUser: UserModel;

    displayedColumns = [
        'firstName', 'lastName', 'email', 'role', 'sapBPNumber'
    ];

    constructor(private _service: UserService) 
    {
        this.dataSource = new UserDataSource(_service);
    }
    
    ngOnInit(): void 
    { 
        this._service.getUsers();
    }

    onSelect(user: UserModel): void 
    {
        this.selectedUser = user;
        this._service.selectedUser.next(user);
    }
}

export class UserDataSource extends MatTableDataSource<UserModel> 
{
    constructor(private _service: UserService) 
    {
        super();
    }

    connect(): BehaviorSubject<UserModel[]> 
    {
        return this._service.users;
    }

    disconnect(): void { }
}
