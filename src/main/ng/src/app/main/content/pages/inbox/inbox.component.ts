import { Component, OnInit } from '@angular/core';
import { InboxService } from './inbox.service';
import { MatSnackBar, MatSnackBarModule } from '@angular/material';
import { Router } from '@angular/router';
import { fuseAnimations } from '@fuse/animations';


@Component({
    selector: 'app-inbox',
    templateUrl: './inbox.component.html',
    styleUrls: ['./inbox.component.scss'],
    animations: fuseAnimations
})
export class InboxComponent implements OnInit {

  

    constructor(private _inboxService: InboxService ,
                private _matSnackBar: MatSnackBar,
                private _router: Router) {
        
    }

    ngOnInit(): void {
    }

    /**
     * reviewEvaluation()
     */
    reviewEvaluation(): void {
        this._router.navigate(['riskModelTemplate/edit/' + this._inboxService.selectedItem.value.riskModelId]);
    }



    /**
     * rejectEvalaution()
     */
    rejectEvaluation(): void {
        console.log ('REJECT SELECTED INBOX ITEM : ' + this._inboxService.selectedItem.value.riskModelId);
        const id = this._inboxService.selectedItem.value.riskModelId;

        this._inboxService.rejectEvaluation(id).subscribe(response => {
            // Refresh the Inbox //TODO
            // this.riskModelTemplate = response;

            this._matSnackBar.open( 'Risk model is rejected and email notification was sent to requestor', 'Ok', { duration: 7000 });
        });
    }

    /**
     * approveEvaluation()
     */
    approveEvaluation(): void {
        console.log ('APPROVE SELECTED INBOX ITEM : ' + this._inboxService.selectedItem.value.riskModelId);
        const id = this._inboxService.selectedItem.value.riskModelId;
        
        this._inboxService.approveEvaluation(id).subscribe(response => {
            // Refresh the Inbox //TODO 
            // this.riskModelTemplate = response;
             
            this._matSnackBar.open( 'Risk model is approved and email notification was sent to requestor', 'Ok', { duration: 7000 });
        });
    }

    /**
     * 
     */
    displayAsPDF(): void {
    }
}
