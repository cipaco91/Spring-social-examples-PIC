/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.showcase.facebook;

import javax.inject.Inject;

import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FacebookFriendsController {

	private final Facebook facebook;

	@Inject
	public FacebookFriendsController(Facebook facebook) {
		this.facebook = facebook;
	}

	@RequestMapping(value="/facebook/friends", method=RequestMethod.GET)
	public String showFeed(Model model) {
		List<FacebookProfile> profiles=new ArrayList<FacebookProfile>();
//		model.addAttribute("friends", facebook.friendOperations().getFriendProfiles());
		List<String> friendIds = facebook.friendOperations().getFriendIds();
		System.out.println(friendIds.size());
		for(String s:friendIds) {
			FacebookProfile firstFriend = facebook.userOperations().getUserProfile(s);
			profiles.add(firstFriend);
			System.out.println(firstFriend.getName());
		}
		model.addAttribute("friends", profiles);
		return "facebook/friends";
	}
	
}
