public record ExchangeRateRespose (
        String base_code,
        String target_code,
        double conversion_rate) {}