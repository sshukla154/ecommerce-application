package com.sshukla.entity;

/**
 * Created by `Seemant Shukla` on 07-05-2023
 */

public enum Role {

	/**
	 * An ecommerce admin typically has access to all administrative features of the ecommerce system,
	 * including the ability to manage products, orders, customers, and settings.
	 * They may also be responsible for managing other user accounts and permissions within the system.
	 */
	ECOMMERCE_ADMIN,
	/**
	 * An ecommerce user is typically a registered customer who has access to their account information,
	 * order history, and other personalized features of the ecommerce website. They may also be able to
	 * save items to a wish list, create product reviews, and receive email notifications about special offers.
	 */
	ECOMMERCE_USER,
	/**
	 * An ecommerce vendor is typically a third-party seller who uses the ecommerce platform to sell their own products.
	 * They may have access to a vendor dashboard where they can manage their products, view sales reports,
	 * and communicate with customers.
	 */
	ECOMMERCE_VENDOR,
	/**
	 * An ecommerce guest is typically a non-registered user who is browsing the website without creating an account.
	 * They may be able to view product information and add items to their cart, but will need to create an account
	 * or proceed to checkout as a guest to complete their purchase.
	 */
	ECOMMERCE_GUEST,
	/**
	 * An ecommerce super admin typically has access to all administrative features of the ecommerce system,
	 * but may have additional privileges and permissions beyond those of a regular ecommerce admin.
	 * This role is typically reserved for the highest level of access within the system and is often
	 * used for system maintenance and troubleshooting.
	 */
	ECOMMERCE_SUPER_ADMIN

}
