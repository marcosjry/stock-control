package marcos.stockcontrol.domain.DTO;

import marcos.stockcontrol.domain.model.Acquisition;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AcquisitionDTO {

    private Long clientId;

    private List<ProductDTO> productList;

    public List<ProductDTO> getProductList() {
        return productList;
    }

    public AcquisitionDTO() {
        this.productList = new ArrayList<>();
    }

    public void setProductList(List<ProductDTO> dtoList) {
        this.productList = dtoList;
    }

    public Acquisition objectTransform() {
        Acquisition acquisition = new Acquisition();
        acquisition.setDate(LocalDateTime.now());
        acquisition.setClientId(this.clientId);
        acquisition.setProducts(this.productList.stream()
                .map(ProductDTO::dtoToPurchased)
                .collect(Collectors.toList()));

        return acquisition;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }




}
