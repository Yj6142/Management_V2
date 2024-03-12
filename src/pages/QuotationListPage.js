import {useParams} from "react-router-dom";
import {getQuotationList, searchQuotation} from "../api/QuotationApi";
import {useEffect, useState} from "react";
import QuotationList from "../components/QuotationList";
import PaginationBox from "../components/PaginationBox";
import "./QuotationListPage.css";
import ItemSearchBox from "../components/ItemSearchBox";
import {orderItem} from "../api/OrderItemApi";

function QuotationListPage(){
    const {companyName} = useParams();
    const [quotations, setQuotations] = useState([]);
    const [currentPage, setCurrentPage] = useState(1);
    const [totalPage, setTotalPage] = useState(0);
    const [initialForm, setInitialForm] = useState({
        searchOption: 1,
        searchData: '',
    });

    const handleLoad = async (page) => {
        try{
            const response = await getQuotationList({companyName, page});
            setQuotations(response.content);
            setTotalPage(response.totalPages);
        } catch (e) {
            console.log("데이터 찾기를 실패했습니다.")
        }
    }

    const handleChangePage = (num) => {
        setCurrentPage(num);
    }

    const handleChange = (e) => {
        setInitialForm({
            ...initialForm,
            [e.target.name]: e.target.value,
        });
    }

    const handleSearch = async (e) => {
        e.preventDefault();

        const formData = new FormData(e.target);
        formData.append("companyName", companyName);

        const searchData = {};

        formData.forEach((value,key) => {
            searchData[key] = value;
        })

        try {
            const findQuotation = await searchQuotation({searchData});
            setQuotations(findQuotation);
            setTotalPage(1);
            setInitialForm({
                searchOption: 1,
                searchData: '',
            });
        } catch (e) {
            console.log("데이터 찾기를 실패하였습니다.");
        }
    }

    const handleOrder = (props) => {
        //quotation 번호 + 주문 수량 서버로 전송
        orderItem(props);
    }

    useEffect(() => {
        handleLoad(currentPage);
    }, [currentPage]);

    return (
        <div className="quotationListBox">
            <h2 onClick={() => window.location.reload()} style={{cursor: 'pointer'}}>{companyName} Quotation List</h2>
            <ItemSearchBox onSearch={handleSearch} onChange={handleChange} initialForm={initialForm}/>
            <QuotationList data={quotations} orderData={handleOrder}/>
            <PaginationBox totalPage={totalPage} active={currentPage} onChangePage={handleChangePage}/>
        </div>
    );
}

export default QuotationListPage;