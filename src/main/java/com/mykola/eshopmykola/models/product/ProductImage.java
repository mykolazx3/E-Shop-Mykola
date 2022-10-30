package com.mykola.eshopmykola.models.product;

import com.mykola.eshopmykola.models.product.Product;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table(name="product_images")
public class ProductImage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "original_file_name")
	private String originalFileName;

	@Column(name = "size")
	private Long size;

	@Column(name = "content_type")
	private String contentType;

	//@Lob//@Column(columnDefinition = "LONGBLOB")
	@Column(name = "bytes", columnDefinition = "LONGBLOB")
	private byte[] bytes;

	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade(org.hibernate.annotations.CascadeType.REFRESH)
	@JoinColumn(name = "product_id", referencedColumnName = "id")
	private Product product;

	public ProductImage() {
	}

	public ProductImage(Long id, String name, String originalFileName, Long size, String contentType,  byte[] bytes, Product product) {
		this.id = id;
		this.name = name;
		this.originalFileName = originalFileName;
		this.size = size;
		this.contentType = contentType;
		this.bytes = bytes;
		this.product = product;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}



	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
