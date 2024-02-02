import './App.css';
import NavigationBar from "./components/NavigationBar";
import {Route, Routes} from "react-router-dom";
import QuotationPage from "./pages/QuotationPage";
import CompanyPage from "./pages/CompanyPage";

function App() {
    return (
        <div className="App">
            <NavigationBar></NavigationBar>
            <Routes>
                <Route path="/quotation/:companyName" element={<QuotationPage></QuotationPage>}>견적 등록 페이지</Route>
                <Route path="/company" element={<CompanyPage></CompanyPage>}>회사 리스트</Route>
            </Routes>
        </div>
    );
}

export default App;
