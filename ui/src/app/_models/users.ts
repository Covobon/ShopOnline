import {User} from "@app/_models/user";
import {Role} from "@app/_models/Role";

export const USERS: User[] = [
  {
    userName: `admin`,
    fullName: `Do Xuan Cuong`,
    password: `1`,
    email: `doxuancuong@gmail.com`,
    address: `hanoi2`,
    phoneNumber: `09235`,
    roles: [],
    createTime: new Date(),
    lastAccess: new  Date(),
  }
]
