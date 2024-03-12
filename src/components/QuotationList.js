import {Button, Table} from "react-bootstrap";
import CurrentDisplay from "./CurrentDisplay";
import QuantityModal from "./modals/QuantityModal";
import {useState} from "react";

function QuotationList({data, orderData}) {

    const [show, setShow] = useState(false);
    const [inputQuantity, setInputQuantity] = useState(0);
    const [saveId, setSaveId] = useState(0);

    const handleShow = (id) => {
        setShow(true);
        setSaveId(id);
    }

    const handleChange = (e) => {
        setInputQuantity(e.target.value);
    }

    const handleClose = () => {
        setShow(false);
    }

    const handleSave = () => {
        orderData({saveId, inputQuantity});
        setShow(false);
        setInputQuantity(0);
        window.alert("저장되었습니다.");
        window.location.reload();
    }

    return (
        <div>
            <Table striped hover bordered>
                <thead>
                <tr>
                    <th>Request Date</th>
                    <th>Article Num</th>
                    <th>Name</th>
                    <th>Ex Price</th>
                    <th>Im Price</th>
                    <th>Order Time</th>
                    <th>주문</th>
                </tr>
                </thead>
                <tbody>
                {
                    data.map(item => (
                        <tr key={item.id}>
                            <td className="col-md-2">{item.requestDate}</td>
                            <td className="col-md-2">{item.articleNum}</td>
                            <td className="col-md-4">{item.name}</td>
                            <td className="col-md-1"><CurrentDisplay price={item.exPrice} currency={item.currencyCode}/></td>
                            <td className="col-md-1"><CurrentDisplay price={item.imPrice} currency="KRW"/></td>
                            <td className="col-md-1">{item.orderTime}</td>
                            <td className="col-md-1"><Button onClick={() => {
                                handleShow(item.id);
                            }}>+</Button></td>
                        </tr>
                    ))
                }
                </tbody>
            </Table>
            <QuantityModal
                show={show}
                handleChange={handleChange}
                handleClose={handleClose}
                handleSave={handleSave}
                quantity={inputQuantity}/>
        </div>
    );
}

export default QuotationList;