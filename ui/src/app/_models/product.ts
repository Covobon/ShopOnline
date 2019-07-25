import {Image} from "@app/_models/image";
import {ProductDetail} from "@app/_models/product-detail";

export class Product{
  productId: string;
  productInfoId: string;
  name: string;
  price: number;
  category: string;
  status: string;
  amount: number;
  images: Image[];
  detail: ProductDetail;
}
