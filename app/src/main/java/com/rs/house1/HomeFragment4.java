package com.rs.house1;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by hp on 4/28/2016.
 */
public class HomeFragment4 extends Fragment {
    TextView textView;

    public HomeFragment4(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.homefragment4, container, false);

        textView=(TextView)rootView.findViewById(R.id.pera2);

        String styledText2 = "<html><body style=\"text-align:justify\"><p><b>Terms and Conditions of Use</b><br/>\n" +
                "The following terms and conditions (the &ldquo;Terms and Conditions&rdquo;) govern your use of this House1 App, and any content made available from or through this house1 app, including any subdomains thereof (the &ldquo;house1.in&rdquo;). The house1 app is made available by QAProgrammer, . We may change the Terms and Conditions from time to time, at any time without notice to you, by posting such changes on the house1 app. BY USING THE APP, YOU ACCEPT AND AGREE TO THESE TERMS AND CONDITIONS AS APPLIED TO YOUR USE OF THE APP. If you do not agree to these Terms and Conditions, you may not access or otherwise use the app<br /> <br />\n" +
                "<b>Proprietary Rights.</b><br /> \n" +
                "As between you and house1 app, solely and exclusively, all rights, title and interest in and to the house1 app, all the content (including, for example, audio, photographs, illustrations, graphics, other visuals, video, copy, text, software, titles, Shockwave files, etc.), code, data and materials thereon, the look and feel, design and organization of the house1 app, and the compilation of the content, code, data and materials on the house1 app, including but not limited to any copyrights, trademark rights, patent rights, database rights, moral rights, sui generis rights and other intellectual property and proprietary rights therein. Your use of the house1 app does not grant to you ownership of any content, code, data or materials you may access on or through the house1 app.<br /> <br />\n" +
                "<b>Limited License.</b><br />\n" +
                "You may access and view the content on the house1 app on your mobile, on website or other device and, unless otherwise indicated in these Terms and Conditions or on the house1.in site, make single copies or prints of the content on the house1.in site for your personal, internal use only. Use of the house1 app and the services offered on or through the house1.in site, are only for your personal, non-commercial use.<br /> <br />\n" +
                "<b>RSS Feeds.</b><br />\n" +
                "RSS (really simple syndication) service is a means by which house1.in offers feeds of story headlines in XML format (&ldquo;RSS Content&rdquo;) to visitors to hosue1.in (the &ldquo;house1.in Site&rdquo;) who use RSS aggregators. These Terms of Use govern your use of the RSS service. The use of the RSS service also is subject to the terms and conditions of the house1 app Service Agreement, which governs the use of house1.in website, information services and content. These Terms of Use and the house1 app or house.in site Interactive Service Agreement may be changed byhouse1 app or house.in site at any time without notice.<br /> <br />\n" +
                "<b>Use of RSS Feeds:</b><br />\n" +
                "RSS is a free service offered by house1 app or house.in site for non-commercial use. Any other uses, including without limitation the incorporation of advertising into or the placement of advertising associated with or targeted towards the RSS Content, are strictly prohibited. You must use the RSS feeds as provided by house1 app or house.in site, and you may not edit or modify the text, content or links supplied by house1 app or house.in site. For web posting, reprint, transcript or licensing requests for Variety material, please send your request to <b>support@house1.in.</b><br /> <br />\n" +
                "<b>Link to Content Pages:</b><br />\n" +
                "The RSS service may be used only with those platforms from which a functional link is made available that, when accessed, takes the viewer directly to the display of the full article on the house1.in Site. You may not display the RSS Content in a manner that does not permit successful linking to, redirection to or delivery of the applicable house1 app or house.in site web page. You may not insert any intermediate page, splash page or other content between the RSS link and the house1 app or house.in site web page.<br /> <br />\n" +
                "<b>Ownership/Attribution:</b><br />\n" +
                "house1 app or house.in site retains all ownership and other rights in the RSS Content, and any and all Variety logos and trademarks used in connection with the RSS Service. You must provide attribution to the appropriate house1 app or house.in site website in connection with your use of the RSS feeds. If you provide this attribution using a graphic, you must use the appropriate house1 app or house.in site website&rsquo;s logo that we have incorporated into the RSS feed.<br /> <br />\n" +
                "<b>Right to Discontinue Feeds:</b><br />\n" +
                "Variety reserves the right to discontinue providing any or all of the RSS feeds at any time and to require you to cease displaying, distributing or otherwise using any or all of the RSS feeds for any reason including, without limitation, your violation of any provision of these Terms of Use. house1 app or house.in site assumes no liability for any of your activities in connection with the RSS feeds or for your use of the RSS feeds in connection with house1 app or house.in site<br /> <br />\n" +
                "<b>Prohibited Use.</b><br />\n" +
                "Any commercial or promotional distribution, publishing or exploitation of the house1 app or house.in site, or any content, code, data or materials on the house1 app or house.in site, is strictly prohibited unless you have received the express prior written permission from authorized personnel of house1 app or house.in siteor the otherwise applicable rights holder. Other than as expressly allowed herein, you may not download, post, display, publish, copy, reproduce, distribute, transmit, modify, perform, broadcast, transfer, create derivative works from, sell or otherwise exploit any content, code, data or materials on or available through the house1 app or house.in site. You further agree that you may not alter, edit, delete, remove, otherwise change the meaning or appearance of, or repurpose, any of the content, code, data, or other materials on or available through the house1 app or house.in site, including, without limitation, the alteration or removal of any trademarks, trade names, logos, service marks, or any other proprietary content or proprietary rights notices. You acknowledge that you do not acquire any ownership rights by downloading any copyrighted material from or through the house1 app or house.in site. If you make other use of the house1 app or house.in site, or the content, code, data or materials thereon or available through the house1 app or house.in site, except as otherwise provided above, you may violate copyright and other laws of the United States, other countries, as well as applicable state laws and may be subject to liability for such unauthorized use.<br /> <br />\n" +
                "<b>Trademarks.</b><br />\n" +
                "The trademarks, logos, service marks and trade names (collectively the &ldquo;Trademarks&rdquo;) displayed on the house1 app or house.in site or on content available through the house1 app or house.in site are registered and unregistered Trademarks of house1 app or house.in site. and others and may not be used in connection with products and/or services that are not related to, associated with, or sponsored by their rights holders that are likely to cause customer confusion, or in any manner that disparages or discredits their rights holders. All Trademarks not owned by house1 app or house.in site that appear on the house1 app or house.in site or on or through the house1 app or house.in site services, if any, are the property of their respective owners. Nothing contained on the house1 app or house.in site should be construed as granting, by implication, or otherwise, any license or right to use any Trademark displayed on the house1 app or house.in site without the written permission of qaprogrammer or the third party that may own the applicabl Trademark. Your misuse of the Trademarks displayed on the house1 app or house.in site or on or through any of the house1 app or house.in site services is strictly prohibited.<br /> <br />\n" +
                "<b>User Information.</b><br />\n" +
                "In the course of your use of the house1 app or house.in site and/or the services made available on or through the house1 app or house.in site, you may be asked to provide certain personalized information to us (such information referred to hereinafter as &ldquo;User Information&rdquo;). house1 app or house.in site information collection and use policies with respect to the privacy of such User Information are set forth in the house1 app or house.in site Privacy Policy which is incorporated herein by reference for all purposes. You acknowledge and agree that you are solely responsible for the accuracy and content of User Information.<br /> <br />\n" +
                "<b>Submitted Materials.</b><br />\n" +
                "Unless specifically requested, we do not solicit nor do we wish to receive any confidential, secret or proprietary information or other material from you through the house1 app or house.in site, by e-mail or in any other way. Any information, creative works, demos, ideas, suggestions, concepts, methods, systems, designs, plans, techniques or other materials submitted or sent to us (including, for example and without limitation, that which you submit or post to our chat rooms, message boards, and/or our blogs, or send to us via e-mail) (&ldquo;Submitted Materials&rdquo;) will be deemed not to be confidential or secret, and may be used by us in any manner consistent with the house1 app or house.in site Privacy Policy. By submitting or sending Submitted Materials to us, you: (i) represent and warrant that the Submitted Materials are original to you, that no other party has any rights thereto, and that any &ldquo;moral rights&rdquo; in Submitted Materials have been waived, and (ii) you grant us and our affiliates a royalty-free, unrestricted, worldwide, perpetual, irrevocable, non-exclusive and fully transferable, assignable and sublicensable right and license to use, copy, reproduce, modify, adapt, publish, translate, create derivative works from, distribute, perform, display and incorporate in other works any Submitted Materials (in whole or part) in any form, media, or technology now known or later developed, including for promotional and/or commercial purposes. We cannot be responsible for maintaining any Submitted Material that you provide to us, and we may delete or destroy any such Submitted Material at any time.<br /> <br />\n" +
                "<b>Prohibited User Conduct.</b><br />\n" +
                "You warrant and agree that, while using the house1 app or house.in site and the various services and features offered on or through the house1 app or house.in site, you shall not: (a) impersonate any person or entity or misrepresent your affiliation with any other person or entity; (b) insert your own or a third party&rsquo;s advertising, branding or other promotional content into any of the house1 app or house.in site content, materials or services (for example, without limitation, in an RSS feed or a podcast received from qaprogrammer or otherwise through the house1 app or house.in site), or use, redistribute, republish or exploit such content or service for any further commercial or promotional purposes; or &copy; attempt to gain unauthorized access to other computer systems through the house1 app or house.in site. You shall not: (i) engage in spidering, &ldquo;screen scraping,&rdquo; &ldquo;database scraping,&rdquo; harvesting of e-mail addresses, wireless addresses or other contact or personal information, or any other automatic means of obtaining lists of users or other information from or through the house1 app or house.in site or the services offered on or through the house1 app or house.in site including without limitation any information residing on any server or database connected to the house1 app or house.in site or the services offered on or through the house1 app or house.in site; (ii) obtain or attempt to obtain unauthorized access to computer systems, materials or information through any means; (iii) use the house1 app or house.in site or the services made available on or through the Web Site in any manner with the intent to interrupt, damage, disable, overburden, or impair the house1 app or house.in site or such services, including, without limitation, sending mass unsolicited messages or &ldquo;flooding&rdquo; servers with requests; (iv) use the house1 app or house.in site or house1 app or house.in site or features in violation of qaprogrammer or any third party&rsquo;s intellectual property or other proprietary or legal rights; or (v) use the house1 app or house.in site or the Web Site&rsquo;s services in violation of any applicable law. You further agree that you shall not attempt (or encourage or support anyone else&rsquo;s attempt) to circumvent, reverse engineer, decrypt, or otherwise alter or interfere with the house1 app or house.in site services, or any content thereof, or make any unauthorized use thereof. You agree that you shall not use the house1 app or house.in site in any manner that could damage, disable, overburden, or impair the house1 app or house.in site or interfere with any other party&rsquo;s use and enjoyment of the house1 app or house.in site or any of its services. You shall not obtain or attempt to obtain any materials or information through any means not intentionally made publicly available or provided for through the house1 app or house.in site.<br /> <br />\n" +
                "<b>Public Forums.</b><br />\n" +
                "qaprogrammer may, from time to time, make messaging services, chat services, bulletin boards, message boards, blogs, other forums and other such services available on or through the house1 app or house.in site. In addition to any other rules or regulations that we may post in connection with a particular service, you agree that you shall not upload, post, transmit, distribute or otherwise publish through the house1 app or house.in site or any service or feature made available on or through the house1 app or house.in site, any materials which (i) restrict or inhibit any other user from using and enjoying the Web Site or the house1 app or house.in site services, (ii) are fraudulent, unlawful, threatening, abusive, harassing, libelous, defamatory, obscene, vulgar, offensive, pornographic, profane, sexually explicit or indecent, (iii) constitute or encourage conduct that would constitute a criminal offense, give rise to civil liability or otherwise violate any local, state, national or international law, (iv) violate, plagiarize or infringe the rights of third parties including, without limitation, copyright, trademark, trade secret, confidentiality, contract, patent, rights of privacy or publicity or any other proprietary right, (v) contain a virus, spyware, or other harmful component, (vi) contain embedded links, advertising, chain letters or pyramid schemes of any kind, or (vii) constitute or contain false or misleading indications of origin, endorsement or statements of fact. You further agree not to impersonate any other person or entity, whether actual or fictitious, including anyone from qaprogrammer. You also may not offer to buy or sell any product or service on or through your comments submitted to our forums. You alone are responsible for the content and consequences of any of your activities.<br /> <br />\n" +
                "<b>Right to Monitor and Editorial Control.</b><br />\n" +
                "Qaprogrammerreserves the right, but does not have an obligation, to monitor and/or review all materials posted to the house1 app or house.in site or through the house1 app or house.in site services or features by users, and qaprogrammer is not responsible for any such materials posted by users. However, qaprogrammer reserves the right at all times to disclose any information as necessary to satisfy any law, regulation or government request, or to edit, refuse to post or to remove any information or materials, in whole or in part, that in qaprogrammer&rsquo;s sole discretion are objectionable or in violation of this Terms of Use, qaprogrammer&rsquo;s policies or applicable law. We may also impose limits on certain features of the forums or restrict your access to part or all of the forums without notice or penalty if we believe you are in breach of the guidelines set forth in this paragraph, our terms and conditions or applicable law, or for any other reason without notice or liability.<br /> <br />\n" +
                "<b>Private or Sensitive Information on Public Forums.</b><br />\n" +
                "It is important to remember that comments submitted to a forum may be recorded and stored in multiple places, both on our house1 app or house.in site and elsewhere on the Internet, which are likely to be accessible for a long time and you have no control over who will read them eventually. It is therefore important that you are careful and selective about the personal information that you disclose about yourself and others, and in particular, you should not disclose sensitive, embarrassing, proprietary or confidential information in your comments to our public forums.<br /><br/>\n" +
                "<b>Linking to the Web Site.</b><br />\n" +
                "You agree that if you include a link from any other web site to the house1 app or house.in site such link shall open in a new browser window and shall link to the full version of an HTML formatted page of this house1 app or house.in site. You are not permitted to link directly to any image hosted on the house1 app or house.in site or our services, such as using an &ldquo;in-line&rdquo; linking method to cause the image hosted by us to be displayed on another web site. You agree not to download or use images hosted on this house1 app or house.in site on another web site, for any purpose, including, without limitation, posting such images on another site. You agree not to link from any other web site to this house1 app or house.in site in any manner such that the Web Site, or any page of the Web Site, is &ldquo;framed,&rdquo; surrounded or obfuscated by any third party content, materials or branding. We reserve all of our rights under the law to insist that any link to the house1 app or house.in site be discontinued, and to revoke your right to link to the house1 app or house.in site from any other web site at any time upon written notice to you.<br /> <br />\n" +
                "<b>Indemnification.</b><br />\n" +
                "You agree to defend, indemnify and hold Variety and Variety&rsquo;s and its affiliates&rsquo; directors, officers, employees and agents harmless from any and all claims, liabilities, costs and expenses, including attorneys&rsquo; fees, arising in any way from your use of the house1 app or house.in site, your placement or transmission of any message, content, information, software or other materials through the house1 app or house.in site, or your breach or violation of the law or of these Terms and Conditions. Qaprogrammerreserves the right, at its own expense, to assume the exclusive defense and control of any matter otherwise subject to indemnification by you, and in such case, you agree to cooperate with qaprogrammer&rsquo;s defense of such claim.<br /> <br />\n" +
                "<b>Orders for Products and Services.</b><br />\n" +
                "We may make certain products available to visitors and registrants of the house1 app or house.in siteIf you order any products, you hereby represent and warrant that you are 18 years old or older. You agree to pay in full the prices for any purchases you make either by credit/debit card concurrent with your online order or by other payment means acceptable to qaprogrammer. You agree to pay all applicable taxes. If payment is not received by us from your credit or debit card issuer or its agents, you agree to pay all amounts due upon demand by us. Certain products that you purchase and/or download on or through the house1 app or house.in site may be subject to additional terms and conditions presented to you at the time of such purchase or download.<br /> <br />\n" +
                "<b>Third Party Web Sites.</b><br />\n" +
                "You may be able to link from the house1 app or house.in site to third party web sites and third party web sites may link to the house1 app or house.in site (&ldquo;Linked Sites&rdquo;). You acknowledge and agree that we have no responsibility for the information, content, products, services, advertising, code or other materials which may or may not be provided by or through Linked Sites, even if they are owned or run by affiliates of ours. Links to Linked Sites do not constitute an endorsement or sponsorship by us of such web sites or the information, content, products, services, advertising, code or other materials presented on or through such web sites. The inclusion of any link to such sites on our house1 app or house.in site does not imply qaprogrammer&rsquo;s endorsement, sponsorship, or recommendation of that site. Qaprogrammerdisclaims any liability for links (1) from another web site to this house1 app or house.in site and (2) to another web site from this house1 app or house.in site. Qaprogrammercannot guarantee the standards of any web site to which links are provided on this house1 app or house.in site nor shall qaprogrammer be held responsible for the contents of such sites, or any subsequent links. qaprogrammer does not represent or warrant that the contents of any third party web site is accurate, compliant with state or federal law, or compliant with copyright or other intellectual property laws. Also, qaprogrammer is not responsible for or any form of transmission received from any linked web site. Any reliance on the contents of a third party web site is done at your own risk and you assume all responsibilities and consequences resulting from such reliance.<br /> <br />\n" +
                "<b>Advertisements and Promotions</b><br />\n" +
                "Qaprogrammer may run advertisements and promotions from third parties on the house1 app or house.in site. Your business dealings or correspondence with, or participation in promotions of, advertisers other than qaprogrammer, and any terms, conditions, warranties or representations associated with such dealings, are solely between you and such third party. qaprogrammer is not responsible or liable for any loss or damage of any sort incurred as the result of any such dealings or as the result of the presence of third-party advertisers on the house1 app or house.in site.<br /> <br />\n" +
                "<b>Copyright Agent.</b><br />\n" +
                "We respect the intellectual property rights of others, and require that the people who use the house1 app or house.in site, or the services or features made available on or through the house1 app or house.in site, do the same. If you believe that your work has been copied in a way that constitutes copyright infringement, please forward the following information to qaprogrammer&rsquo;s Copyright Agent, designated as such pursuant to the Digital Millennium Copyright Act, 17 U.S.C. &sect; 512&copy;(2), named below:<br />\n" +
                "&bull; Plot No 112, SGR Town Ship Hayat Nagar,<br />\n" +
                "Hyderbad 501510, INDIA ,<br />\n" +
                "Phone: 040-65 70 57 57, (or) Fax: +9140-65437634, support@house.in<br />\n" +
                "&bull; copyrighted work: providing all house needs like plumber , carpenter &#8230;.etc;<br />\n" +
                "&bull; infringing material is located: India<br />\n" +
                "&bull; Regards<br />\n" +
                "&bull; Gopi Raghavendranath&amp; Gopi Muralidhar<br />\n" +

                "&bull; A statement by house1 app or house.in site, made under penalty of perjury, that the above information in your notice is accurate and that you are the copyright owner or are authorized to act on the copyright owner&rsquo;s behalf.<br /> <br />\n" +
                "<b>DISCLAIMER OF WARRANTIES.</b><br />\n" +
                "THE house1 app or house.in site INCLUDING, WITHOUT LIMITATION, ALL SERVICES, CONTENT, FUNCTIONS AND MATERIALS PROVIDED THROUGH THE house1 app or house.in site, ARE PROVIDED &ldquo;AS IS,&rdquo; &ldquo;AS AVAILABLE,&rdquo; WITHOUT WARRANTY OF ANY KIND, EITHER EXPRESS OR IMPLIED, INCLUDING, WITHOUT LIMITATION, ANY WARRANTY FOR INFORMATION, DATA, DATA PROCESSING SERVICES, UPTIME OR UNINTERRUPTED ACCESS, ANY WARRANTIES CONCERNING THE AVAILABILITY, PLAYABILITY, DISPLAYABILITY, ACCURACY, PRECISION, CORRECTNESS, THOROUGHNESS, COMPLETENESS, USEFULNESS, OR CONTENT OF INFORMATION, AND ANY WARRANTIES OF TITLE, NON-INFRINGEMENT, MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE, AND WE HEREBY DISCLAIM ANY AND ALL SUCH WARRANTIES, EXPRESS AND IMPLIED. WE DO NOT WARRANT THAT THE WEB SITE OR THE SERVICES, CONTENT, FUNCTIONS OR MATERIALS PROVIDED THROUGH THE WEB SITE WILL BE TIMELY, SECURE, UNINTERRUPTED OR ERROR FREE, OR THAT DEFECTS WILL BE CORRECTED. WE MAKE NO WARRANTY THAT THE WEB SITE OR THE PROVIDED SERVICES WILL MEET USERS&rsquo; REQUIREMENTS. NO ADVICE, RESULTS OR INFORMATION, WHETHER ORAL OR WRITTEN, OBTAINED BY YOU FROM US OR THROUGH THE house1 app or house.in site SHALL CREATE ANY WARRANTY NOT EXPRESSLY MADE HEREIN. Qaprogrammer ALSO ASSUMES NO RESPONSIBILITY, AND SHALL NOT BE LIABLE FOR, ANY DAMAGES TO, OR VIRUSES THAT MAY INFECT, YOUR EQUIPMENT ON ACCOUNT OF YOUR ACCESS TO, USE OF, OR BROWSING IN THE house1 app or house.in site OR YOUR DOWNLOADING OF ANY MATERIALS, DATA, TEXT, IMAGES, VIDEO CONTENT, OR AUDIO CONTENT FROM THE house1 app or house.in site. IF YOU ARE DISSATISFIED WITH THE house1 app or house.in site, YOUR SOLE REMEDY IS TO DISCONTINUE USING THE house1 app or house.in site. WE TRY TO ENSURE THAT THE INFORMATION POSTED ON THE house1 app or house.in site IS CORRECT AND UP-TO-DATE. WE RESERVE THE RIGHT TO CHANGE OR MAKE CORRECTIONS TO ANY OF THE INFORMATION PROVIDED ON THE house1 app or house.in site AT ANY TIME AND WITHOUT ANY PRIOR WARNING. Qaprogrammer NEITHER ENDORSES NOR IS RESPONSIBLE FOR THE ACCURACY OR RELIABILITY OF ANY OPINION, ADVICE OR STATEMENT ON THE house1 app or house.in site, NOR FOR ANY OFFENSIVE, DEFAMATORY, OBSCENE, INDECENT, UNLAWFUL OR INFRINGING POSTING MADE THEREON BY ANYONE OTHER THAN AUTHORIZED QAProgrammer EMPLOYEE SPOKESPERSONS WHILE ACTING IN THEIR OFFICIAL CAPACITIES (INCLUDING, WITHOUT LIMITATION, OTHER USERS OF THE house1 app or house.in site). IT IS YOUR RESPONSIBILITY TO EVALUATE THE ACCURACY, COMPLETENESS OR USEFULNESS OF ANY INFORMATION, OPINION, ADVICE OR OTHER CONTENT AVAILABLE THROUGH THE house1 app or house.in site PLEASE SEEK THE ADVICE OF PROFESSIONALS, AS APPROPRIATE, REGARDING THE EVALUATION OF ANY SPECIFIC INFORMATION, OPINION, ADVICE OR OTHER CONTENT, INCLUDING BUT NOT LIMITED TO FINANCIAL, HEALTH, OR LIFESTYLE INFORMATION, OPINION, ADVICE OR OTHER CONTENT. PRIOR TO THE EXECUTION OF A PURCHASE OR SALE OF ANY SECURITY OR INVESTMENT, YOU ARE ADVISED TO CONSULT WITH YOUR BROKER OR OTHER FINANCIAL ADVISOR TO VERIFY PRICING AND OTHER INFORMATION. WE SHALL HAVE NO LIABILITY FOR INVESTMENT DECISIONS BASED UPON, OR THE RESULTS OBTAINED FROM, THE CONTENT PROVIDED HEREIN. NOTHING CONTAINED IN THE house1 app or house.in site SHALL BE CONSTRUED AS INVESTMENT ADVICE. Qaprogrammer IS NOT A REGISTERED BROKER-DEALER OR INVESTMENT ADVISOR AND DOES NOT GIVE INVESTMENT ADVICE OR RECOMMEND ONE PRODUCT OVER ANOTHER. WITHOUT LIMITATION OF THE ABOVE IN THIS SECTION, qaprogrammer AND ITS AFFILIATES, SUPPLIERS AND LICENSORS MAKE NO WARRANTIES OR REPRESENTATIONS REGARDING ANY PRODUCTS OR SERVICES ORDERED OR PROVIDED VIA THE house1 app or house.in site, AND HEREBY DISCLAIM, AND YOU HEREBY WAIVE, ANY AND ALL WARRANTIES AND REPRESENTATIONS MADE IN PRODUCT OR SERVICES LITERATURE, FREQUENTLY ASKED QUESTIONS DOCUMENTS AND OTHERWISE ON THE house1 app or house.in site OR IN CORRESPONDENCE WITH qaprogrammer OR ITS AGENTS. ANY PRODUCTS AND SERVICES ORDERED OR PROVIDED VIA THE WEB SITE ARE PROVIDED BY qaprogrammer&ldquo;AS IS,&rdquo; EXCEPT TO THE EXTENT, IF AT ALL, OTHERWISE SET FORTH IN A LICENSE OR SALE AGREEMENT SEPARATELY ENTERED INTO IN WRITING BETWEEN YOU AND qaprogrammerOR ITS LICENSOR OR SUPPLIER.<br /> <br />\n" +
                "<b>LIMITATION OF LIABILITY.</b><br />\n" +
                "IN NO EVENT, INCLUDING BUT NOT LIMITED TO NEGLIGENCE, SHALL QAProgrammer, OR ANY OF ITS DIRECTORS, OFFICERS, EMPLOYEES, AGENTS OR CONTENT OR SERVICE PROVIDERS (COLLECTIVELY, THE &ldquo;PROTECTED ENTITIES&rdquo;) BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL, INCIDENTAL, CONSEQUENTIAL, EXEMPLARY OR PUNITIVE DAMAGES ARISING FROM, OR DIRECTLY OR INDIRECTLY RELATED TO, THE USE OF, OR THE INABILITY TO USE, THE house1 app or house.in site OR THE CONTENT, MATERIALS AND FUNCTIONS RELATED THERETO, YOUR PROVISION OF INFORMATION VIA THE house1 app or house.in site, LOST BUSINESS OR LOST SALES, EVEN IF SUCH PROTECTED ENTITY HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES. SOME JURISDICTIONS DO NOT ALLOW THE LIMITATION OR EXCLUSION OF LIABILITY FOR INCIDENTAL OR CONSEQUENTIAL DAMAGES SO SOME OF THE ABOVE LIMITATIONS MAY NOT APPLY TO CERTAIN USERS. IN NO EVENT SHALL THE PROTECTED ENTITIES BE LIABLE FOR OR IN CONNECTION WITH ANY CONTENT POSTED, TRANSMITTED, EXCHANGED OR RECEIVED BY OR ON BEHALF OF ANY USER OR OTHER PERSON ON OR THROUGH THE house1 app or house.in site. IN NO EVENT SHALL THE TOTAL AGGREGATE LIABILITY OF THE PROTECTED ENTITIES TO YOU FOR ALL DAMAGES, LOSSES, AND CAUSES OF ACTION (WHETHER IN CONTRACT OR TORT, INCLUDING, BUT NOT LIMITED TO, NEGLIGENCE OR OTHERWISE) ARISING FROM THE TERMS AND CONDITIONS OR YOUR USE OF THE house1 app or house.in site EXCEED, IN THE AGGREGATE, THE AMOUNT, IF ANY, PAID BY YOU TO qaprogrammer FOR YOUR USE OF THE house1 app or house.in site OR PURCHASE OF PRODUCTS VIA THE house1 app or house.in site<br /> <br />\n" +
                "<b>Photosensitive Seizures.</b><br />\n" +
                "A very small percentage of people may experience a seizure when exposed to certain visual images, such as flashing lights or patterns that may appear in video games or other electronic or online content. Even people who have no history of seizures or epilepsy may have an undiagnosed condition that can cause these &ldquo;photosensitive epileptic seizures&rdquo; while watching video games or other electronic content. These seizures have a variety of symptoms, including lightheadedness, disorientation, confusion, momentary loss of awareness, eye or face twitching, altered vision or jerking or shaking of arms or legs. If you experience any of the foregoing symptoms, or if you or your family has a history of seizures or epilepsy, you should immediately stop using the house1 app or house.in site and consult a doctor.<br /> <br />\n" +"<b>Applicable Laws.</b><br />\n" +
                "We control and operate the house1 app or house.in site from our offices in the india. We do not represent that materials on the house1 app or house.in site are appropriate or available for use in other locations. Persons who choose to access the Web Site from other locations do so on their own initiative, and are responsible for compliance with local laws, if and to the extent local laws are applicable. All parties to these terms and conditions waive their respective rights to a trial by jury.<br /> <br />\n" +
                "<b>Termination.</b><br />\n" +
                "Qaprogrammer may terminate, change, suspend or discontinue any aspect of the house1 app or house.in site services at any time. qaprogrammer may restrict, suspend or terminate your access to the house1 app or house.in site and/or its services if we believe you are in breach of our terms and conditions or applicable law, or for any other reason without notice or liability. qaprogrammer maintains a policy that provides for the termination in appropriate circumstances of the house1 app or house.in site use privileges of users who are repeat infringers of intellectual property rights.<br /> <br />\n" +
                "<b>Changes to Terms of Use.</b><br />\n" +
                "qaprogrammer reserves the right, at its sole discretion, to change, modify, add or remove any portion of the Terms and Conditions, in whole or in part, at any time. Changes in the Terms and Conditions will be effective when posted. Your continued use of the house1 app or house.in site and/or the services made available on or through the house1 app or house.in site after any changes to the Terms and Conditions are posted will be considered acceptance of those changes.<br /> <br />\n" +
                "<b>Miscellaneous.</b><br />\n" +
                "The Terms and Conditions, and the relationship between you and us, shall be governed by the laws of the india, , without regard to conflict of law provisions. You agree that any cause of action that may arise under the Terms and Conditions shall be commenced and be heard in the appropriate court in india. You agree to submit to the personal and exclusive jurisdiction of the courts located india. Our failure to exercise or enforce any right or provision of the Terms and Conditions shall not constitute a waiver of such right or provision. If any provision of the Terms and Conditions is found by a court of competent jurisdiction to be invalid, the parties nevertheless agree that the court should endeavor to give effect to the parties&rsquo; intentions as reflected in the provision, and the other provisions of the Terms and Conditions remain in full force and effect.<br /> <br />\n" +
                "<b>Supplemental Terms.</b><br />\n" +
                "Getty Images Notice: Getty Images still images and visual representations may not be republished, retransmitted, reproduced, downloaded or otherwise used, except for downloading for personal, non-commercial use.</p>\n" +
                "\n" +
                "<p></p>\n" +
                "\n" +
                "<p>\n" +
                "</p></body></html> ";
        // textView2.setText(styledText2);
        textView.setText(Html.fromHtml(styledText2), TextView.BufferType.SPANNABLE);


        return rootView;

    }
}
