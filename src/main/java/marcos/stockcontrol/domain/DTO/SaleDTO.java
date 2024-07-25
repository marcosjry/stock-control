package marcos.stockcontrol.domain.DTO;

import marcos.stockcontrol.domain.model.Sale;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SaleDTO {

    private Long clientId;

    private List<ProductDTO> productList;

    public SaleDTO() {
        this.productList = new ArrayList<>();
    }

    public Sale objectTransform() {
        Sale sale = new Sale();
        sale.setDate(LocalDateTime.now());
        sale.setClientId(this.clientId);
        sale.setProducts(this.productList.stream()
                .map(ProductDTO::dtoToPurchased)
                .collect(Collectors.toList()));

        return sale;
    }

    public List<ProductDTO> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductDTO> productList) {
        this.productList = productList;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }


}
