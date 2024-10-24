package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import dto.CartTm;
import dto.Item;
import dto.OrderDetails;
import dto.Orders;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import service.BoFactory;
import service.custom.PlaceOrderService;
import utill.ServiceType;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PlaceOrderFormController implements Initializable {

    PlaceOrderService placeOrderService = BoFactory.getInstance().getServiceType(ServiceType.PLACEORDER);
    ObservableList<CartTm> cartTmObservableList = FXCollections.observableArrayList();

    @FXML
    public JFXTextField txtDiscount;

    @FXML
    public JFXTextField lblEmployeeId;

    @FXML
    public TableColumn colSize;

    @FXML
    public Label lblItemSize;

    @FXML
    public Spinner spinnerQty;

    @FXML
    private JFXButton btnPlaceOrder;

    @FXML
    private JFXRadioButton btnRadioCash;

    @FXML
    private JFXRadioButton btnRadioCreadit;

    @FXML
    private JFXRadioButton btnRadioDebit;

    @FXML
    private JFXButton btnSearch;

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colProduct;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private Label lblItemCode;

    @FXML
    private Label lblItemDescription;

    @FXML
    private Label lblItemPrice;

    @FXML
    private Label lblItemQtyOnHand;

    @FXML
    private Label lblNetAmount;

    @FXML
    private Label lblOrderId;

    @FXML
    private TableView<CartTm> tblItemCart;

    @FXML
    private JFXTextField txtItemCode;


    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        List<OrderDetails> orderDetails = new ArrayList<>();

        cartTmObservableList.forEach(cartTm -> {
            orderDetails.add(new OrderDetails(
                    lblOrderId.getText(),
                    cartTm.getItemCode(),
                    cartTm.getSize(),
                    cartTm.getQuantity(),
                    Integer.parseInt(txtDiscount.getText())
            ));
        });
        Orders order = new Orders(
                lblOrderId.getText(),
                LocalDate.now(),
                lblEmployeeId.getText(),
                checkPaymentType(),
                Double.parseDouble(lblNetAmount.getText()),
                orderDetails
        );

        try {
            placeOrderService.placeOrder(order);
        }catch (Exception ex){
            throw new RuntimeException();
        }
    }

    private String checkPaymentType() {
        if(btnRadioCash.isSelected()){
            return "cash payment";
        }else if(btnRadioDebit.isSelected()){
            return "debit card";
        } else if (btnRadioCreadit.isSelected()) {
            return "credit card";
        }
        return null;
    }

    @FXML
    void btnRadioCashOnaction(ActionEvent event) {
        btnPlaceOrder.setDisable(false);
    }

    @FXML
    void btnRadioCreditOnAction(ActionEvent event) {
        btnPlaceOrder.setDisable(false);
    }

    @FXML
    void btnRadioDebitOnAction(ActionEvent event) {
        btnPlaceOrder.setDisable(false);
    }

    @FXML
    void txtItemCodeOnAction(ActionEvent event) {
        Item loadItem = placeOrderService.loadItemInfo(txtItemCode.getText());


        lblItemCode.setText(loadItem.getItemCode());
        lblItemDescription.setText(loadItem.getDescription());
        lblItemPrice.setText(String.valueOf(loadItem.getUnitPrice()));
        lblItemQtyOnHand.setText(String.valueOf(loadItem.getQtyOnHand()));
        lblItemSize.setText(loadItem.getSize());

        spinnerQty.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,loadItem.getQtyOnHand()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //-------------------ITEM TABLE----------------------//
        colProduct.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        //----------------------------------------------------//

        btnPlaceOrder.setDisable(true);

        //-------------RADIO BUTTON--------------//
        ToggleGroup radioGroup = new ToggleGroup();
        btnRadioCash.setToggleGroup(radioGroup);
        btnRadioCreadit.setToggleGroup(radioGroup);
        btnRadioDebit.setToggleGroup(radioGroup);
        //---------------------------------------//

        loadOrderId();

        spinnerQty.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,100));
    }

    public void btnAddToCartOnAction(ActionEvent actionEvent) {
        addItemsToCart();
        setNetTotal();
    }

    private void addItemsToCart() {
        cartTmObservableList.add(new CartTm(
                lblItemCode.getText(),
                lblItemDescription.getText(),
                lblItemSize.getText(),
                Double.parseDouble(lblItemPrice.getText()),
                Integer.parseInt(String.valueOf(spinnerQty.getValue())),
                Double.parseDouble(lblItemPrice.getText())*Integer.parseInt(String.valueOf(spinnerQty.getValue()))
        ));

        tblItemCart.setItems(cartTmObservableList);
    }

    private void setNetTotal(){
        lblNetAmount.setText(placeOrderService.calNetTotal(cartTmObservableList));
    }

    private void loadOrderId(){
        lblOrderId.setText(placeOrderService.generateOrderId());
    }

}
