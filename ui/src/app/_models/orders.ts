import {OrdersProducts} from "@app/_models/orders-product";


export class Orders {
  orderId: number;
  userName: string;
  status: string;
  address: string;
  createTime: Date;
  ordersProducts: OrdersProducts[];
  pay: number;
}
