import ItemTable from "../components/ItemTable";
import {useParams} from "react-router-dom";
import ItemSearchBox from "../components/ItemSearchBox";
import {useState} from "react";
import {searchItem} from "../api/QuotationApi";

function QuotationPage() {

    const {companyName} = useParams();
    const [initialForm, setInitialForm] = useState({
        searchOption : 1,
        searchData : '',
    })
    const [item, setItem] = useState([]);

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

    return (
        <div>
            <h2>{companyName} Quotation</h2>
            <ItemSearchBox
                initialForm={initialForm}
                onChange={handleChange}
                onSearch={handleSearch}
            />
            <ItemTable data={item}></ItemTable>
        </div>
    );
}

export default QuotationPage;
