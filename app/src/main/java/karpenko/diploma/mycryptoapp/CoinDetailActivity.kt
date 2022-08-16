package karpenko.diploma.mycryptoapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import karpenko.diploma.mycryptoapp.viewModel.CoinViewModel
import kotlinx.android.synthetic.main.activity_coin_detail.*

class CoinDetailActivity() : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_detail)

        if (!intent.hasExtra(EXTRA_FROM_SYMBOL)) {
            finish()
            return
        }
        val fromSymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL)

        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]

        fromSymbol?.let {
            viewModel.getDetailInfo(it).observe(this, Observer {
                tvPriceValue.text = it.price.toString()
                tvMaxPriceValue.text = it.highDay.toString()
                tvMinPriceValue.text = it.lowDay.toString()
                tvLastDealValue.text = it.lastMarket
                tvLastUpdateValue.text = it.getFormattedTime()
                tvPair.text = "${it.fromSymbol} / ${it.toSymbol}"
                Picasso.get().load(it.getFullImageUrl()).into(imageView)
            })
        }
    }

    companion object {
        private const val EXTRA_FROM_SYMBOL = "fSym"

        fun newIntent(context: Context, fromSymbol: String): Intent{
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(EXTRA_FROM_SYMBOL, fromSymbol)
            return intent
        }

    }
}