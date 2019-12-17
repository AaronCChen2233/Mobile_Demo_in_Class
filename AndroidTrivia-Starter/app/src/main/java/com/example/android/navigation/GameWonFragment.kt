/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.navigation

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.android.navigation.databinding.FragmentGameWonBinding
import timber.log.Timber


class GameWonFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding: FragmentGameWonBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_game_won, container, false)
        binding.nextMatchButton.setOnClickListener {
            it.findNavController().navigate(GameWonFragmentDirections.actionGameWonFragmentToGameFragment())
        }
        val args = GameWonFragmentArgs.fromBundle(arguments!!)

        Timber.i("$args.numCorrect")
        Timber.i("$args.numQuestions")
        Toast.makeText(context, "Correct: ${args.numCorrect}, Questions: ${args.numQuestions}", Toast.LENGTH_LONG).show()
        /**say have an options menu*/
        setHasOptionsMenu(true)

        return binding.root
    }

    private fun getShareInten(): Intent {
        val args = GameWonFragmentArgs.fromBundle(arguments!!)

        /**implicit intent*/
        val shareIntent = Intent(Intent.ACTION_SEND)
        /**Here will use MIME type*/
        shareIntent.setType("text/plain")
        shareIntent.putExtra(Intent.EXTRA_TEXT, "${args.numCorrect}/${args.numQuestions}")
        return shareIntent
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.winner_menu, menu)
        /**if there's no activity that can handle the intent
         * then just hide the share button*/

        if (getShareInten().resolveActivity(activity!!.packageManager) == null) {
            /**can't handle hide the share button*/
            menu?.findItem(R.id.share)?.setVisible(false)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.share -> startActivity(getShareInten())
        }
        return super.onOptionsItemSelected(item)
    }
}
