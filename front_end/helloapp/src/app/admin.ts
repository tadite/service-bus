export class Admin{
    name: string;
    eMail: string;
    password: string;

    constructor(name: string, eMail: string, password: string) {
        this.name = name;
        this.eMail = eMail;
        this.password = password;
    }
}
