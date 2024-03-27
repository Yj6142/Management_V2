import {useEffect, useState} from "react";
import {getExchangeRateInfo} from "../api/ExchangeRateApi";
import ExchangeRateInfoBox from "../components/ExchangeRateInfoBox";
import MenuList from "../components/MenuList";
import "./MainPage.css";

function MainPage () {

    const [exchangeRateList, setExchangeRateList] = useState([]);

    const handleLoad = async () => {
        try {
            const data = await getExchangeRateInfo();
            setExchangeRateList(data);
        } catch (e) {
            console.log("데이터를 불러오는데 실패했습니다.");
        }
    }


    useEffect(() => {
        handleLoad();
    }, []);


    return (
        <div className="mainContainer">
            <div className="exchangeRate">
                <ExchangeRateInfoBox data={exchangeRateList}/>
            </div>
            <MenuList/>
        </div>
    );
}

export default MainPage;