import {Image} from "@app/_models/image";
import {ProductInfo} from "@app/_models/product-info";

export class Product{
  productId: string;
  productInfoId: string;
  name: string;
  price: number;
  category: string;
  status: string;
  amount: number;
  images: Image[];
  productInfo: ProductInfo;
}
