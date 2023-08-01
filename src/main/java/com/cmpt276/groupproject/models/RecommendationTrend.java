package com.cmpt276.groupproject.models;

public class RecommendationTrend {

        private int buy;
        private int hold;
        private String period;
        private int sell;
        private int strongBuy;
        private int strongSell;
        private String symbol;

        public RecommendationTrend() {
        }

        public RecommendationTrend(int buy, int hold, String period, int sell, int strongBuy, int strongSell,
                String symbol) {
            this.buy = buy;
            this.hold = hold;
            this.period = period;
            this.sell = sell;
            this.strongBuy = strongBuy;
            this.strongSell = strongSell;
            this.symbol = symbol;
        }

        public int getBuy() {
            return buy;
        }

        public void setBuy(int buy) {
            this.buy = buy;
        }

        public int getHold() {
            return hold;
        }

        public void setHold(int hold) {
            this.hold = hold;
        }

        public String getPeriod() {
            return period;
        }

        public void setPeriod(String period) {
            this.period = period;
        }

        public int getSell() {
            return sell;
        }

        public void setSell(int sell) {
            this.sell = sell;
        }

        public int getStrongBuy() {
            return strongBuy;
        }

        public void setStrongBuy(int strongBuy) {
            this.strongBuy = strongBuy;
        }

        public int getStrongSell() {
            return strongSell;
        }

        public void setStrongSell(int strongSell) {
            this.strongSell = strongSell;
        }

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        
    
        
        
    
}
