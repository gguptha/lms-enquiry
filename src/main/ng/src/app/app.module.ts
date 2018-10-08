import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule, Routes } from '@angular/router';
import { MatMomentDateModule } from '@angular/material-moment-adapter';
import { MatButtonModule, MatIconModule, DateAdapter } from '@angular/material';
import { TranslateModule } from '@ngx-translate/core';
import 'hammerjs';

import { FuseModule } from '@fuse/fuse.module';
import { FuseSharedModule } from '@fuse/shared.module';
import { FuseProgressBarModule, FuseSidebarModule, FuseThemeOptionsModule } from '@fuse/components';

import { fuseConfig } from 'app/fuse-config';

import { AppComponent } from 'app/app.component';
import { LayoutModule } from 'app/layout/layout.module';
import { SampleModule } from 'app/main/sample/sample.module';
import { EnquiryApplicationModule } from './main/content/pages/enquiry/enquiryApplication/enquiryApplication.module';
import { EnquiryAlertsModule } from './main/content/pages/enquiry/enquiryAlerts/enquiryAlerts.module';
import { EnquiryListModule } from './main/content/pages/enquiry/enquirySearch/enquirySearch.module';
import { LocalDateFormat } from './main/content/others/localDate.Format';

const appRoutes: Routes = [
    {
        path: '**',
        redirectTo: 'loanenquiry'
    }
];

@NgModule({
    declarations: [
        AppComponent
    ],
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        HttpClientModule,
        RouterModule.forRoot(appRoutes),

        TranslateModule.forRoot(),

        // Material moment date module
        MatMomentDateModule,

        // Material
        MatButtonModule,
        MatIconModule,

        // Fuse modules
        FuseModule.forRoot(fuseConfig),
        FuseProgressBarModule,
        FuseSharedModule,
        FuseSidebarModule,
        FuseThemeOptionsModule,

        // App modules
        LayoutModule,
        SampleModule,

        // User modules
        EnquiryApplicationModule,
        EnquiryAlertsModule,
        EnquiryListModule
    ],
    bootstrap: [
        AppComponent
    ],
    providers: [
        { provide: DateAdapter, useClass: LocalDateFormat }
    ],
})
export class AppModule {
}
