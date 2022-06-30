package com.moon.myapplication.ui.food

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.moon.myapplication.databinding.FragmentFoodBinding
import java.util.*
import kotlin.collections.ArrayList

class FoodFragment : Fragment() {

    private lateinit var dataFoods: ArrayList<FoodData>
    private lateinit var foodAdapter: FoodAdapter
    private var _binding: FragmentFoodBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFoodBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed({
            foodAdapter = FoodAdapter()
            dataFoods = ArrayList()
            dataFoods.clear()
            dataFoods.addAll(generateDataFood())
            foodAdapter.setListData(dataFoods)
            foodAdapter.onItemClick = {
                val action = FoodFragmentDirections.actionNavigationFoodToFoodDetailFragment(it)
                findNavController().navigate(action)
            }
            binding.rvFood.adapter = foodAdapter
            binding.progressCircular.visibility = View.GONE
        }, 3000)

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filter(newText.toString())
                return true
            }
        })
    }

    private fun filter(text: String) {
        val filteredList: ArrayList<FoodData> = ArrayList()
        for (i in dataFoods) {
            if (i.name.lowercase(Locale.getDefault()).contains(text.lowercase(Locale.getDefault()))) {
                filteredList.add(i)
            }
        }
        binding.apply {
            if (filteredList.isNotEmpty()) {
                foodAdapter.filterList(filteredList)
                binding.rvFood.visibility = View.VISIBLE
            } else {
                binding.rvFood.visibility = View.GONE
            }
        }
    }

    private fun generateDataFood(): ArrayList<FoodData> {
        val listData = ArrayList<FoodData>()
        listData.clear()
        listData.add(
            FoodData(
                "https://www.masakapahariini.com/wp-content/uploads/2019/01/gado-gado-MAHI.jpg",
                "Gado-gado",
                "Gado-gado adalah makanan khas Jakarta berisi sayur-sayuran yang direbus, irisan telur dan tahu, serta ditaburi bawang goreng dan kerupuk. Sayur-sayuran ditambahkan dengan bumbu kacang atau saus dari kacang tanah yang telah dihaluskan yang kemudian diaduk merata."
            )
        )

        listData.add(
            FoodData(
                "https://selerasa.com/wp-content/uploads/2021/11/Resep-Batagor-Bandung-1200x798.jpg",
                "Batagor",
                "Batagor merupakan jajanan khas Bandung yang mengadaptasi gaya Tionghoa-Indonesia dan kini sudah dikenal hampir di seluruh wilayah Indonesia."
            )
        )

        listData.add(
            FoodData(
                "https://www.resepkekinian.com/wp-content/uploads/2021/04/Klepon-Ketan-Pandan.jpg",
                "Klepon",
                "Klepon, atau kelepon, adalah makanan ringan dari bola kue beras manis yang diisi dengan gula aren cair dan dilapisi kelapa parut. Berasal dari Jawa, bola ketan berwarna hijau adalah salah satu kue tradisional yang populer dalam masakan Indonesia."
            )
        )

        listData.add(
            FoodData(
                "https://asset.kompas.com/crops/BiN_oCxCDgkO09b1B_FPfZuvGBw=/0x0:739x493/750x500/data/photo/2020/01/29/5e318845429db.jpg",
                "Ketoprak",
                "Ketoprak adalah salah satu jenis makanan khas Indonesia menggunakan ketupat yang mudah dijumpai. Biasanya ketoprak dijajakan menggunakan kereta dorong di jalan-jalan atau kaki lima"
            )
        )

        listData.add(
            FoodData(
                "https://www.masakapahariini.com/wp-content/uploads/2019/01/bubur-ayam-kuning.jpg",
                "Bubur ayam",
                "Bubur ayam adalah salah satu jenis makanan bubur dari Indonesia. Bubur nasi adalah beras yang dimasak dengan air yang banyak sehingga memiliki tekstur yang lembut dan berair. Bubur biasanya disajikan dalam suhu panas atau hangat."
            )
        )

        listData.add(
            FoodData(
                "https://img.okezone.com/content/2020/05/26/298/2220040/cara-memasak-nasi-uduk-pakai-rice-cooker-gampang-banget-deh-mYKq3oaPvF.jpg",
                "Nasi uduk",
                "Nasi uduk adalah hidangan yang dibuat dari nasi putih yang diaron dan dikukus dengan santan, serta dibumbui dengan pala, kayu manis, jahe, daun serai dan merica. Hidangan ini mulai dibuat penduduk pulau Jawa sekitar tahun 1910-1924 dan dipopulerkan oleh Hindia Belanda setelahnya."
            )
        )

        return listData
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}