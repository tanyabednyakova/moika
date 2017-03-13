package io.khasang.moika.controller;

import io.khasang.moika.dao.ProductDao;
import io.khasang.moika.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductsController {
    @Autowired
    private ProductDao productDao;

    @RequestMapping(value = "/shop/products", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<Product> getProductList() {
        return productDao.getProductList(); //"redirect:yandex.ru";
    }

    @RequestMapping(value = "/shop/products/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Product getProductById(@PathVariable(value = "id") String id) {
        return productDao.getProductById(Long.parseLong(id));
    }

    @RequestMapping(value = "/shop/products/byname", method = RequestMethod.GET)
    @ResponseBody
    public Product getProductByName(@RequestParam("name") String name) {
        return productDao.getProductByName(name);
    }

    @RequestMapping(value = "/shop/products/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Product addNewProduct(@RequestBody Product product) {
        productDao.addNewProduct(product);
        return product;
    }

    @RequestMapping(value = "/shop/products/addamount", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void addProductById(@RequestBody @RequestParam("productId") String productId,
                               @RequestParam("amount") String amount) {
        long id = Long.parseLong(productId);
        int productAmount = Integer.parseInt(amount);
        Product product = productDao.getProductById(id);
        product.setAmount(product.getAmount() + productAmount);
        productDao.updateProduct(product);
    }

    @RequestMapping(value = "/shop/products/changeprice", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void changeProductPriceById(@RequestBody Product product) {
        long id = product.getId();
        double price = product.getPrice();
        product = productDao.getProductById(id);
        product.setPrice(price);
        productDao.updateProduct(product);
    }

}