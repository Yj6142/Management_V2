import {useEffect, useState} from "react";
import {getDailyProfit, getProfitList} from "../api/TotalProfitApi";
import ProfitList from "../components/ProfitList";
import CalendarBox from "../components/CalendarBox";
import "./TodayProfitPage.css";
import CurrentDisplay from "../components/CurrentDisplay";

function TodayProfitPage() {

    const [totalProfit, setTotalProfit] = useState([]);
    const [dailyProfit, setDailyProfit] = useState([]);
    const [dailyTotalProfit, setDailyTotalProfit] = useState(0);
    const [searchDate, setSearchDate] = useState(new Date());

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
            setDailyProfit(response.profitList);
            setSearchDate(date);
            setDailyTotalProfit(response.totalProfit);
        } catch (e) {
            console.log("데이터를 불러오는데 실패했습니다.");
        }
    }

    useEffect(() => {
        handleLoad();
    }, []);

    return (
        <div className="profitContainer">
            <div className="profitList">
                <div className="profitHeader">
                    <h3 style={{margin: '0px 20px'}}>Profit List</h3>
                    <CalendarBox onSubmit={handleSubmit}/>
                </div>
                <ProfitList data={totalProfit}/>
            </div>
            <div className="profitLine"></div>
            <div className="dailyProfitList">
                <h3 style={{
                    borderBottom: '1px solid black',
                    paddingBottom: '10px'
                }}>Daily Profit</h3>
                <ProfitList data={dailyProfit}/>
                <span>
                    {`+ ${searchDate.getMonth() + 1}월 ${searchDate.getDate()}일 총 이윤 +`}
                    <CurrentDisplay price={dailyTotalProfit} currency="KRW"></CurrentDisplay>
                </span>
            </div>
        </div>
    );
}

export default TodayProfitPage;