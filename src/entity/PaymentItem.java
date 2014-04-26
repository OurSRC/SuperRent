/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

/**
 *
 * @author Jingchuan Chen
 */
public class PaymentItem {

    /**
     * @return the itemId
     */
    public int getItemId() {
        return itemId;
    }

    /**
     * @param itemId the itemId to set
     */
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    /**
     * @return the paymentId
     */
    public int getPaymentId() {
        return paymentId;
    }

    /**
     * @param paymentId the paymentId to set
     */
    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    /**
     * @return the type
     */
    public ITEMTYPE getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(ITEMTYPE type) {
        this.type = type;
    }
    
    /**
     * @param type the String contains type name to set
     */
    public void setType(String type) {
        if (type == null) {
            this.type = null;
        } else {
            this.type = ITEMTYPE.valueOf(type);
        }
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public enum ITEMTYPE {
        VEHICLE(1),
        EQUIPMENT(2),
        DELAY(3),
        MILE(4),
        DAMAGE(5),
        MEMBERSHIP(6),
        TICKET(7),
        POINTEXCHANGE(8),
        INSURANCE(9),
        FUEL(10),
        OTHER(11);
        
        private int value;
        
        private ITEMTYPE(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
    
    private int itemId;
    private int paymentId;
    private ITEMTYPE type;
    private String name;
    private int price;
    private int quantity;

    public PaymentItem() {
    }

    public PaymentItem(int paymentId, ITEMTYPE type, String name, int price, int quantity) {
        this.paymentId = paymentId;
        this.type = type;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}
