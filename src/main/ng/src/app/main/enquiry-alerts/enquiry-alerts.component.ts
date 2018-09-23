import { Component } from '@angular/core';
import { fuseAnimations } from '../../../@fuse/animations';
import { ActivatedRoute, Router } from '@angular/router';
import { EnquiryAlertsService } from './enquiry-alerts.service';
import { BehaviorSubject } from 'rxjs';

@Component({
    selector   : 'enquiry-alerts',
    templateUrl: './enquiry-alerts.component.html',
    styleUrls  : ['./enquiry-alerts.component.scss'],
    animations : fuseAnimations
})
export class EnquiryAlertsComponent {

    /**
     * constructor()
     * @param _route
     */
    constructor(private _route: ActivatedRoute, private _service: EnquiryAlertsService, private _router: Router) {
        // Initialize EnquiryAlertsService.loanApplication.
        this._service.loanApplications = new BehaviorSubject(_route.snapshot.data.routeResolvedData[0]);
    }

    redirectToEnquiryReview(): void {
        this._router.navigate(['/enquiryreview']);
    }
}
