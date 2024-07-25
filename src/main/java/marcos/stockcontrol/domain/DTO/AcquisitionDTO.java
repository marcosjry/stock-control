package marcos.stockcontrol.domain.DTO;

import marcos.stockcontrol.domain.model.Acquisition;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AcquisitionDTO {

    private Long supplierId;

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
        acquisition.setSupplierId(this.supplierId);
        acquisition.setProducts(this.productList.stream()
                .map(ProductDTO::dtoToPurchased)
                .collect(Collectors.toList()));

        return acquisition;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }




}
