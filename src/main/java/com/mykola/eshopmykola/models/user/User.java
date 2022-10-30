package com.mykola.eshopmykola.models.user;

import com.mykola.eshopmykola.models.order.Order;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	private Role role;

	@OneToOne(mappedBy = "bucketOwner")
	@Cascade({org.hibernate.annotations.CascadeType.REMOVE, org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	private Bucket bucket;

	@OneToMany(mappedBy = "orderOwner")
	@Cascade({org.hibernate.annotations.CascadeType.REMOVE, org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	private List<Order> orders;

	@Column(name = "uuid")
	private String uuid;

	public User() {
	}

	public User(String firstName, String lastName, String email, String password, Role role, Bucket bucket, List<Order> orders, String uuid) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.role = role;
		this.bucket = bucket;
		this.orders = orders;
		this.uuid = uuid;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Bucket getBucket() {
		return bucket;
	}

	public void setBucket(Bucket bucket) {
		this.bucket = bucket;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public User(String firstName, String lastName, String email, String password, Role role) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public static BuilderUser builder() {
		return new BuilderUser();
	}

	public static class BuilderUser {
		private String firstName;
		private String lastName;
		private String email;
		private String password;
		private Role role;

		BuilderUser() {

		}

		public BuilderUser lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public BuilderUser firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public BuilderUser email(String email) {
			this.email = email;
			return this;
		}

		public BuilderUser password(String password) {
			this.password = password;
			return this;
		}

		public BuilderUser role(Role role) {
			this.role = role;
			return this;
		}

		public User build() {
			return new User(this.firstName, this.lastName, this.email, this.password, this.role);
		}
	}

}
