import ItemTable from "../components/ItemTable";
import {useParams} from "react-router-dom";
import ItemSearchBox from "../components/ItemSearchBox";
import {useState} from "react";
import {addQuotation, searchItem} from "../api/QuotationApi";
import QuotationTable from "../components/QuotationTable";
import {Button} from "react-bootstrap";

function QuotationPage() {

    const {companyName} = useParams();
    const [initialForm, setInitialForm] = useState({
        searchOption : 1,
        searchData : '',
    })
    const [item, setItem] = useState([]);
    const [quotationList, setQuotationList] = useState([]);

    const handleChange = (e) => {
        setInitialForm({
            ...initialForm,
            [e.target.name]: e.target.value,
        });
    }

    const handleSearch = async (e) =>{
        e.preventDefault();

        const formData = new FormData(e.target);
        formData.append("companyName", companyName);
        const searchData = {};

        formData.forEach((value, key) => {
            searchData[key] = value;
        })

        try{
            const findItem = await searchItem(searchData);
            setItem(findItem);
            setInitialForm({
                searchOption: 1,
                searchData: '',
            })
        } catch (e) {
            console.log("아이템을 검색하지 못했습니다.");
        }
    }

    const handleEdit = (props) => {
        setItem(prevItem => {
            return prevItem.map(item => {
                if(item.id === props.editId){
                    return {
                        ...item,
                        exPrice : props.inputPrice,
                    }
                }
                return item;
            })
        })
    }

    const handleSubmit = (id) => {
        const selectItem = item.find(n => n.id === id);
        const isAlreadyExist = quotationList.some(item => item.id === id);

        if(isAlreadyExist){
            window.alert("이미 추가된 아이템 입니다.");
        } else {
            setQuotationList(prevQuotationList => [...prevQuotationList, {
                id: selectItem.id,
                articleNum: selectItem.articleNum,
                name: selectItem.name,
                exPrice: selectItem.exPrice,
                currencyCode: selectItem.currencyCode,
            }])
        }
    }

    const handleDelete = (id) => {
        const resultQuotationList = quotationList.filter(item => item.id !== id);
        setQuotationList(resultQuotationList);
    }

    const handleSave = async () => {
        try{
            const itemIdList = quotationList.map(item => {
                return {
                    itemId: item.id,
                    exPrice: item.exPrice
                };
            })
            await addQuotation({companyName, itemIdList});
        } catch (e) {
            console.log("데이터를 저장하는데 실패하였습니다.");
        }
    }

    return (
        <div>
            <h2>Item Search</h2>
            <ItemSearchBox
                initialForm={initialForm}
                onChange={handleChange}
                onSearch={handleSearch}
            />
            <ItemTable data={item} editData={handleEdit} handleSubmit={handleSubmit}/>
            <h2>{companyName} Quotation</h2>
            <Button onClick={handleSave}>저장</Button>
            <QuotationTable data={quotationList} handleDelete={handleDelete}/>
        </div>
    );
}

export default QuotationPage;
