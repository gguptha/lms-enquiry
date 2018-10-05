import { FuseNavigation } from '@fuse/types';

export const navigation: FuseNavigation[] = [
    {
        id       : 'applications',
        title    : 'Applications',
        translate: 'NAV.APPLICATIONS',
        type     : 'group',
        children : [
            {
                id       : 'loan-enquiry',
                title    : 'Loan Enquiry',
                translate: 'NAV.LOANENQUIRY',
                type     : 'item',
                icon     : 'email',
                url      : '/enquiryApplication',
            },
            {
                id       : 'enquiry-alerts',
                title    : 'Enquiry Alerts',
                translate: 'NAV.ENQUIRYALERTS',
                type     : 'item',
                icon     : 'email',
                url      : '/enquiryAlerts',
            },
            {
                id       : 'enquiry-list',
                title    : 'Enquiry List',
                translate: 'NAV.ENQUIRYLIST',
                type     : 'item',
                icon     : 'email',
                url      : '/enquiryList',
            },
            {
                id       : 'sample',
                title    : 'Sample',
                translate: 'NAV.SAMPLE.TITLE',
                type     : 'item',
                icon     : 'email',
                url      : '/sample',
                badge    : {
                    title    : '25',
                    translate: 'NAV.SAMPLE.BADGE',
                    bg       : '#F44336',
                    fg       : '#FFFFFF'
                }
            }
        ]
    }
];
