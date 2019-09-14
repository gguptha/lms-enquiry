export class MailObject
{

  id: string;
  toAddress: string;
  dateTime: string;
  subject: string;
  mailContent: string;
  sendingApp: string;
  appObjectId: string;




    constructor(mailObject: any)
    {
        this.id = mailObject.id;
        this.toAddress = mailObject.toAddress;
        this.dateTime = mailObject.dateTime;
        this.subject = mailObject.subject;
        this.mailContent = mailObject.mailContent;
        this.sendingApp = mailObject.sendingApp;
        this.appObjectId = mailObject.appObjectId;

    }



}
