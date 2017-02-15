import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
public class Coin implements CoinInterface {
    private static final Set<Integer> COINS_ACCEPTED = new HashSet<>(
        Arrays.asList(new Integer[]{5, 10, 25, 100})
    );

    @Override
    public boolean validCoin(final int coin) {
        return !!(COINS_ACCEPTED.contains(coin));
    }
}
