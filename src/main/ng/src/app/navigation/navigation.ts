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
                id       : 'administration',
                title    : 'Administration',
                translate: 'NAV.ADMINISTRATION',
                type     : 'group',
                icon     : 'email',
                children : [
                    {
                        id       : 'user-management',
                        title    : 'User Management',
                        translate: 'NAV.USERMANAGEMENT',
                        type     : 'item',
                        icon     : 'email',
                        url      : '/userManagement',
                    },
                ]
            }
        ]
    }
];
