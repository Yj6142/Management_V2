import {useEffect, useState} from "react";
import {getDailyProfit, getProfitList} from "../api/TotalProfitApi";
import ProfitList from "../components/ProfitList";
import CalendarBox from "../components/CalendarBox";

function TodayProfitPage() {

    const [totalProfit, setTotalProfit] = useState([]);
    const [dailyProfit, setDailyProfit] = useState([]);

    const handleLoad = async () => {
        try{
            const response = await getProfitList();
            setTotalProfit(response);
        } catch (e) {
            console.log("데이터를 불러오는데 실패했습니다.");
        }
    }

    const handleSubmit = async (date) => {
        try{
            const response = await getDailyProfit({date});
            setDailyProfit(response);
        } catch (e) {
            console.log("데이터를 불러오는데 실패했습니다.");
        }
    }

    useEffect(() => {
        handleLoad();
    }, []);

    return (
        <div>
            <h2>Profit</h2>
            <CalendarBox onSubmit={handleSubmit}/>
            <ProfitList data={totalProfit}/>
            <h2>Daily Profit</h2>
            <ProfitList data={dailyProfit} />
        </div>
    );
}

export default TodayProfitPage;