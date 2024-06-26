import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import {getOrderList, getOrdersFilteredByDate} from "../api/OrderItemApi";
import PaginationBox from "../components/PaginationBox";
import OrderList from "../components/OrderList";
import CalendarBox from "../components/CalendarBox";
import "./OrderListPage.css";

function OrderListPage() {

    const {companyName} = useParams();
    const [currentPage, setCurrentPage] = useState(1);
    const [totalPage, setTotalPage] = useState(0);
    const [orderList, setOrderList] = useState([]);

    const handleLoad = async (page) => {
        try {
            const response = await getOrderList({companyName, page});
            setOrderList(response.content);
            setTotalPage(response.totalPages);
        } catch (e) {
            console.log("데이터를 불러오는데 실패하였습니다.");
        }
    }

    const handleChangePage = (num) => {
        setCurrentPage(num);
    }

    const handleSubmit = async (date) => {
        console.log(date);
        const response = await getOrdersFilteredByDate({companyName, date});
        setOrderList(response);
        setTotalPage(1);
    };

    useEffect(() => {
        handleLoad(currentPage);
    }, [currentPage]);

    return (
        <div className="orderBox">
            <div className="headerBox">
                <h3>{companyName} Order List</h3>
                <CalendarBox onSubmit={handleSubmit}/>
            </div>
            <div className="orderList">
                <OrderList data={orderList}/>
                <PaginationBox totalPage={totalPage} active={currentPage} onChangePage={handleChangePage}/>
            </div>
        </div>
    );


}

export default OrderListPage;