import axios from "axios";

const orderItem = async (props) => {
    try{
        const response = await axios.post("http://localhost:8080/orders", {
            "quotationId": props.saveId,
            "quantity": props.inputQuantity,
        });
        return response.data;
    } catch (e) {
        console.log("데이터를 저장하는데 실패하였습니다.");
    }
}

export {orderItem};