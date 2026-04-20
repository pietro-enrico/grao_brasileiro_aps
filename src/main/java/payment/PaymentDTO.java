package payment;

public class PaymentDTO {
    private Float value;
    private String categoria, sub_categoria, quantity, payment_type;
    private static StringBuilder errors = new StringBuilder();

    public String getCategoria(){
        return categoria;
    }

    public void setCategoria(String categoria) {
        if (categoria.isEmpty()){
            errors.append("\n - O campo categoria é obrigatório o preenchimento!" );
        } else {
            this.categoria = categoria;
        }
    }

    public String getSub_categoria() {
        return sub_categoria;
    }

    public void setSub_categoria(String sub_categoria){
        if (!categoria.equals("Dinheiro") && sub_categoria.isEmpty()) {
            errors.append("\n - O campo subcategoria é obrigatório o preenchimento!");
        }
        else {
            this.sub_categoria = sub_categoria;
        }
    }

    public String getQuantity(){
        return quantity;
    }

    public void setQuantity(String quantity){
        if (quantity.isEmpty()){
            errors.append("\n - O campo quantidade é obrigatório o preenchimento!");
        }else{
            this.quantity = quantity;
        }
    }

    public float getValue(){
        return value;
    }

    public void setValue(Float value){
        if (value.isNaN()){
            errors.append("\n - O campo valor é obrigatório o preenchimento!");
        } else {
            this.value = value;
        }
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type){
        if (payment_type.isEmpty()){
            errors.append("\n - O campo tipo de pagamento é obrigatório o preenchimento");
        } else{
            this.payment_type = payment_type;
        }
    }

    public static StringBuilder getErrors() { return errors; }

    public static void setErrors(String errors) {
        PaymentDTO.errors = new StringBuilder(errors);
    }

}
