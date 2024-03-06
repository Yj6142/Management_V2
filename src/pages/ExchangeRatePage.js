import {useEffect, useState} from "react";
import {getExchangeRateInfo} from "../api/ExchangeRateApi";
import ExchangeRateInfoBox from "../components/ExchangeRateInfoBox";

function ExchangeRatePage () {

    const [exchangeRateList, setExchangeRateList] = useState([]);

    const handleLoad = async () => {
        try {
            const data = await getExchangeRateInfo();
            setExchangeRateList(data);
        } catch (e) {
            console.log("데이터를 불러오는데 실패했습니다.");
        }
    }

    const date = new Date();

    useEffect(() => {
        handleLoad();
    }, []);


    return (
        <div>
            <h2>{`${date.getMonth()}월 ${date.getDate()}일 ${date.getHours()}시 `}환율 정보</h2>
            <ExchangeRateInfoBox data={exchangeRateList}/>
        </div>
    );
}

export default ExchangeRatePage;