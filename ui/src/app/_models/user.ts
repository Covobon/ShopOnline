import {Role} from '@app/_models/Role';

export class User{
  userName: string;
  fullName: string;
  email: string;
  address: string;
  phoneNumber: string;
  roles: Role[];
  createTime: Date;
  lastAccess: Date;
  authdata ?: string;
}
