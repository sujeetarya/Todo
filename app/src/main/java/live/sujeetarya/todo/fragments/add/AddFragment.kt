package live.sujeetarya.todo.fragments.add

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import live.sujeetarya.todo.R
import live.sujeetarya.todo.data.models.Priority
import live.sujeetarya.todo.data.models.ToDoData
import live.sujeetarya.todo.data.viewmodel.ToDoViewModel
import live.sujeetarya.todo.databinding.FragmentAddBinding
import live.sujeetarya.todo.fragments.SharedViewModel

class AddFragment : Fragment() {

    private val mToDoViewModel: ToDoViewModel by viewModels()
    private val mSharedViewModel:SharedViewModel by viewModels()

    private var _binding: FragmentAddBinding? = null
    private  val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAddBinding.inflate(layoutInflater, container, false)

        // Set Menu
        setHasOptionsMenu(true)

        // Spinner Item Selected Listener
        binding.currentSpinnerPriority.onItemSelectedListener = mSharedViewModel.listener

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.menuAdd) {
            insertDataToDb()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun insertDataToDb() {
        val mTitle = binding.etCurrentTitle.text.toString()
        val mPriority = binding.currentSpinnerPriority.selectedItem.toString()
        val mDescription = binding.etCurrentDescription.text.toString()

        val validation = mSharedViewModel.verifyDataFromUser(mTitle, mDescription)
        if (validation) {
            // Insert data to database
            val newData = ToDoData(0, mTitle, mSharedViewModel.parsePriority(mPriority), mDescription)

            mToDoViewModel.insertData(newData)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_SHORT).show()
            // Navigate Back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Plz fill out all the fields", Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}