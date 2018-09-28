import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { EnquiryAlertsService } from './enquiryAlerts.service';
import { BehaviorSubject } from 'rxjs';
import { fuseAnimations } from '@fuse/animations'

@Component({
    selector   : 'fuse-enquiry-alerts-component',
    templateUrl: './enquiryAlerts.component.html',
    styleUrls  : ['./enquiryAlerts.component.scss'],
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
        // Set EnquiryAlertsService.selectedLoanApplicationId to undefined.
        this._service.selectedLoanApplicationId = undefined;
    }

    redirectToEnquiryReview(): void {
        this._router.navigate(['/enquiryreview']);
    }
}
