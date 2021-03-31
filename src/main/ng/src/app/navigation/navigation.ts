import {FuseNavigation} from '@fuse/types';

export const navigation: FuseNavigation[] = [
  {
    id: 'applications',
    title: 'Applications',
    translate: 'NAV.APPLICATIONS',
    type: 'group',
    children: [
      {
        id: 'loan-enquiry',
        title: 'New Loan Enquiry',
        translate: 'NAV.LOANENQUIRY',
        type: 'item',
        icon: 'create',
        url: '/enquiryApplication',
      },
      {
        id: 'enquiry-alerts',
        title: 'Enquiry Alerts',
        translate: 'NAV.ENQUIRYALERTS',
        type: 'item',
        icon: 'rate_review',
        url: '/enquiryAlerts',
      },
      {
        id: 'enquiry-list',
        title: 'Enquiry List',
        translate: 'NAV.ENQUIRYLIST',
        type: 'item',
        icon: 'list',
        url: '/enquiryList',
      }
    ]
  }
];

export const officerNavigation: FuseNavigation[] = [
  {
    id: 'applications',
    title: 'Applications',
    translate: 'NAV.APPLICATIONS',
    type: 'group',
    children: [
    {
        id: 'inbox',
        title: 'Inbox',
        translate: 'NAV.INBOX',
        type: 'item',
        icon: 'create',
        url: '/inbox',
    },
    {
        id: 'New loan-enquiry',
        title: 'New Loan Enquiry',
        translate: 'NAV.LOANENQUIRY',
        type: 'item',
        icon: 'create',
        url: '/enquiryApplication',
      },
      {
        id: 'enquiry-alerts',
        title: 'Enquiry Alerts',
        translate: 'NAV.ENQUIRYALERTS',
        type: 'item',
        icon: 'rate_review',
        url: '/enquiryAlerts',
      },
      {
        id: 'enquiry-list',
        title: 'Enquiry List',
        translate: 'NAV.ENQUIRYLIST',
        type: 'item',
        icon: 'list',
        url: '/enquiryList',
      },
      {
        id: 'partner-mgmt',
        title: 'Business Partners',
        translate: 'NAV.PARTNER',
        type: 'item',
        icon: 'people_alt',
        url: '/partner',
      }
    ]
  }
];

export const adminNavigation: FuseNavigation[] = [
    {
        id: 'inbox',
        title: 'Inbox',
        translate: 'NAV.INBOX',
        type: 'item',
        icon: 'account_box',
        url: '/inbox'
    },
    {
    id: 'administration',
    title: 'Administration',
    translate: 'NAV.ADMINISTRATION',
    type: 'group',
    icon: 'account_box',
    children: [
      {
        id: 'user-management',
        title: 'User Management',
        translate: 'NAV.USERMANAGEMENT',
        type: 'item',
        icon: 'account_box',
        url: '/userManagement',
      },
      {
        id: 'mail-repo',
        title: 'Email Events',
        translate: 'NAV.MAILREPOSITORY',
        type: 'item',
        icon: 'mail',
        url: '/mailRepo',
      }
    ]
  },
  {
    id: 'applications',
    title: 'Applications',
    translate: 'NAV.APPLICATIONS',
    type: 'group',
    children: [
      {
        id: 'loan-enquiry',
        title: 'New Loan Enquiry',
        translate: 'NAV.LOANENQUIRY',
        type: 'item',
        icon: 'create',
        url: '/enquiryApplication',
      },
      {
        id: 'enquiry-alerts',
        title: 'Enquiry Alerts',
        translate: 'NAV.ENQUIRYALERTS',
        type: 'item',
        icon: 'rate_review',
        url: '/enquiryAlerts',
      },
      {
        id: 'enquiry-list',
        title: 'Enquiry List',
        translate: 'NAV.ENQUIRYLIST',
        type: 'item',
        icon: 'list',
        url: '/enquiryList',
      },
      {
        id: 'partner-mgmt',
        title: 'Business Partners',
        translate: 'NAV.PARTNER',
        type: 'item',
        icon: 'people_alt',
        url: '/partner',
      }
    ]
  },
  {
    id: 'monitoring',
    title: 'Monitoring',
    translate: 'NAV.MONITORING',
    type: 'group',
    children: [
      {
        id: 'loan-contracts',
        title: 'Loan Contracts List',
        translate: 'NAV.LOANCONTRACTSLIST',
        type: 'item',
        icon: 'create',
        url: '/loanContractsList',
      }
    ]
  }
];
