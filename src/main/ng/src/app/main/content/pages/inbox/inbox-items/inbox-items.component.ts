import { Component, OnInit } from '@angular/core';
import { InboxService } from '../inbox.service';
import { ActivatedRoute } from '@angular/router';
import { fuseAnimations } from '@fuse/animations';
import { BehaviorSubject } from 'rxjs';

@Component({
    selector: 'app-inbox-items',
    templateUrl: './inbox-items.component.html',
    styleUrls: ['./inbox-items.component.scss'],
    animations: fuseAnimations
})
export class InboxItemsComponent implements OnInit {

    inboxItems: any;
    
    inboxItem: any;
    selectedItem: any;

    displayedColumns = [
        'dateAsString', 'projectType', 'riskLevel', 'projectName', 'riskModelId', 'requestedBy'
    ];

    /**
     * 
     * @param _service: InboxService
     * @param _route: ActivatedRoute
     */
    constructor(private _service: InboxService, _route: ActivatedRoute) {
        // Fetch evaluations from route resolved data.
        _route.data.subscribe((data) => {
            this.inboxItems = data.routeResolvedData;
            console.log('inboxItems', this.inboxItems);
        });
    }

    /**
     * 
     */
    ngOnInit(): void {
    }

    onSelect(inboxItem: any): void {
        this.selectedItem = inboxItem;
        this._service.selectedItem = new BehaviorSubject(inboxItem);
    }
}
