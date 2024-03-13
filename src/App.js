import './App.css';
import NavigationBar from "./components/NavigationBar";
import {Route, Routes} from "react-router-dom";
import QuotationPage from "./pages/QuotationPage";
import CompanyPage from "./pages/CompanyPage";
import QuotationListPage from "./pages/QuotationListPage";
import ExchangeRatePage from "./pages/ExchangeRatePage";
import OrderListPage from "./pages/OrderListPage";
import TodayProfitPage from "./pages/TodayProfitPage";

function App() {
    return (
        <div className="App">
            <NavigationBar></NavigationBar>
            <Routes>
                <Route path="/quotation/:companyName" element={<QuotationPage></QuotationPage>}>견적 등록 페이지</Route>
                <Route path="/quotations/:companyName" element={<QuotationListPage></QuotationListPage>}>Quotation List 페이지</Route>
                <Route path="/company" element={<CompanyPage></CompanyPage>}>회사 리스트</Route>
                <Route path="/todayRate" element={<ExchangeRatePage></ExchangeRatePage>}>오늘의 환율 페이지</Route>
                <Route path="/orders" element={<TodayProfitPage></TodayProfitPage>}>오늘의 이윤 페이지</Route>
                <Route path="/orders/:companyName" element={<OrderListPage></OrderListPage>}>주문서 페이지</Route>
            </Routes>
        </div>
    );
}

export default App;
