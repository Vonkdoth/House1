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
public class HomeFragment3 extends Fragment {
    TextView textView,textView2,textView3,textView4;
    public HomeFragment3(){

    }

    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState){
        View rootView=inflater.inflate(R.layout.homefragment3,container,false);

       // textView=(TextView)rootView.findViewById(R.id.text1);
        textView2=(TextView)rootView.findViewById(R.id.pera);
        textView3=(TextView)rootView.findViewById(R.id.hed);
        textView4=(TextView)rootView.findViewById(R.id.pera2);


//        String styledText = "<font color='blue' size='30'><u>house1.in</u></font>.<b>Privacy Policy</b>";
//        textView.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);

        String styledText2 = "<html><body style=\"text-align:justify\"> This privacy policy has been compiled to better serve those who are concerned with how their 'Personally identifiable information' (PII) is being used online. PII, as used in US privacy law and information security, is information that can be used on its own or with other information to identify, contact, or locate a single person, or to identify an individual in context. Please read our privacy policy carefully to get a clear understanding of how we collect, use, protect or otherwise handle your Personally Identifiable Information in accordance with our website.</body></html> ";
       // textView2.setText(styledText2);
        textView2.setText(Html.fromHtml(styledText2), TextView.BufferType.SPANNABLE);


        String styledText3 = "<font color='black' size='30'></font><b>What personal information do we collect from the people that visit our blog, website or app?</b>";
        textView3.setText(Html.fromHtml(styledText3), TextView.BufferType.SPANNABLE);

        String styledText4 = "<font color='black' size='30'></font>When ordering or registering on our site, as appropriate, you may be asked to enter your App will helps to their daily problems like painting, home tutions or other details to help you with your experience.<p><b>When do we collect information?</b><br />\n" +
                " <br />\n" +
                "We collect information from you when you fill out a form or enter information on our site.</p>\n" +
                "\n" +
                "<p><b>How do we use your information?</b><br />\n" +
                " <br />\n" +
                "We may use the information we collect from you when you register, make a purchase, sign up for our newsletter, respond to a survey or marketing communication, surf the website, or use certain other site features in the following ways:<br />\n" +
                " &bull; To allow us to better service you in responding to your customer service requests.<br />\n" +
                " <br />\n" +
                "<b>How do we protect visitor information?</b><br />\n" +
                " <br />\n" +
                "Our website is scanned on a regular basis for security holes and known vulnerabilities in order to make your visit to our site as safe as possible.<br />\n" +
                "We use regular Malware Scanning.<br />\n" +
                "Your personal information is contained behind secured networks and is only accessible by a limited number of persons who have special access rights to such systems, and are required to keep the information confidential. In addition, all sensitive/credit information you supply is encrypted via Secure Socket Layer (SSL) technology.<br />\n" +
                " <br />\n" +
                "We implement a variety of security measures when a user places an order enters, submits, or accesses their information to maintain the safety of your personal information.<br />\n" +
                " <br />\n" +
                "For your convenience we may store your credit card information longer than 30 days in order to expedite future orders, and to automate the billing process.</p>\n" +
                "\n" +
                "<p><b>Do we use 'cookies'?</b><br />\n" +
                " <br />\n" +
                "We do not use cookies for tracking purposes</p>\n" +
                "\n" +
                "<p>You can choose to have your computer warn you each time a cookie is being sent, or you can choose to turn off all cookies. You do this through your browser (like Internet Explorer) settings. Each browser is a little different, so look at your browser's Help menu to learn the correct way to modify your cookies.<br />\n" +
                " <br />\n" +
                "If you disable cookies off, some features will be disabled that make your site experience more efficient and some of our services will not function properly.<br />\n" +
                " <br />\n" +
                "However, you can still place orders .</p>\n" +
                "\n" +
                "<p><b>Third-party disclosure</b><br />\n" +
                " <br />\n" +
                "We do not sell, trade, or otherwise transfer to outside parties your personally identifiable information unless we provide users with advance notice. This does not include website hosting partners and other parties who assist us in operating our website, conducting our business, or serving our users, so long as those parties agree to keep this information confidential. We may also release information when it's release is appropriate to comply with the law, enforce our site policies, or protect ours or others' rights, property, or safety. </p>\n" +
                "\n" +
                "<p>However, non-personally identifiable visitor information may be provided to other parties for marketing, advertising, or other uses.<br />\n" +
                " <br />\n" +
                "<b>Third-party links</b><br />\n" +
                " <br />\n" +
                "We do not include or offer third-party products or services on our website.<br />\n" +
                " <br />\n" +
                "<b>Google</b><br />\n" +
                " <br />\n" +
                "Google's advertising requirements can be summed up by Google's Advertising Principles. They are put in place to provide a positive experience for users.https:<u><font color=blue>//support.google.com/adwordspolicy/answer/1316548?hl=en</font></u> <br />\n" +
                "We have not enabled Google AdSense on our site but we may do so in the future.<br />\n" +
                " <br />\n" +
                "<b>California Online Privacy Protection Act</b><br />\n" +
                " <br />\n" +
                "CalOPPA is the first state law in the nation to require commercial websites and online services to post a privacy policy. The law's reach stretches well beyond California to require a person or company in the United States (and conceivably the world) that operates websites collecting personally identifiable information from California consumers to post a conspicuous privacy policy on its website stating exactly the information being collected and those individuals with whom it is being shared, and to comply with this policy. - See more at:<u><font color=blue> http://consumercal.org/california-online-privacy-protection-act-caloppa/#sthash.0FdRbT51.dpuf</font></u></p>\n" +
                "\n" +
                "<p><b>According to CalOPPA we agree to the following:</b><br />\n" +
                "Users can visit our site anonymously.<br />\n" +
                "Once this privacy policy is created, we will add a link to it on our home page or as a minimum on the first significant page after entering our website.<br />\n" +
                "Our Privacy Policy link includes the word 'Privacy' and can be easily be found on the page specified above.</p>\n" +
                "\n" +
                "<p>Users will be notified of any privacy policy changes:<br />\n" +
                " &bull; On our Privacy Policy Page<br />\n" +
                "Users are able to change their personal information:<br />\n" +
                " &bull; By emailing us<br />\n" +
                " &bull; By calling us<br />\n" +
                " &bull; By logging in to their account<br />\n" +
                " &bull; By chatting with us or sending us a ticket</p>\n" +
                "\n" +
                "<p><b>How does our site handle do not track signals?</b><br />\n" +
                "We honor do not track signals and do not track, plant cookies, or use advertising when a Do Not Track (DNT) browser mechanism is in place.</p>\n" +
                "\n" +
                "<p><b>Does our site allow third-party behavioral tracking?</b><br />\n" +
                "It's also important to note that we do not allow third-party behavioral tracking<br />\n" +
                " <br />\n" +
                "<b>COPPA (Children Online Privacy Protection Act)</b><br />\n" +
                " <br />\n" +
                "When it comes to the collection of personal information from children under 13, the Children's Online Privacy Protection Act (COPPA) puts parents in control. The Federal Trade Commission, the nation's consumer protection agency, enforces the COPPA Rule, which spells out what operators of websites and online services must do to protect children's privacy and safety online.<br />\n" +
                "We do not specifically market to children under 13.<br />\n" +
                " <br />\n" +
                "<b>Fair Information Practices</b><br />\n" +
                " <br />\n" +
                "The Fair Information Practices Principles form the backbone of privacy law in the United States and the concepts they include have played a significant role in the development of data protection laws around the globe. Understanding the Fair Information Practice Principles and how they should be implemented is critical to comply with the various privacy laws that protect personal information.<br />\n" +
                "<b>In order to be in line with Fair Information Practices we will take the following responsive action, should a data breach occur:</b><br />\n" +
                "We will notify the users via in-site notification<br />\n" +
                " &bull; Within 7 business days</p>\n" +
                "\n" +
                "<p>We also agree to the Individual Redress Principle, which requires that individuals have a right to pursue legally enforceable rights against data collectors and processors who fail to adhere to the law. This principle requires not only that individuals have enforceable rights against data users, but also that individuals have recourse to courts or government agencies to investigate and/or prosecute non-compliance by data processors.<br />\n" +
                " <br />\n" +
                "<b>CAN SPAM Act</b><br />\n" +
                " <br />\n" +
                "The CAN-SPAM Act is a law that sets the rules for commercial email, establishes requirements for commercial messages, gives recipients the right to have emails stopped from being sent to them, and spells out tough penalties for violations.<br />\n" +
                "<b>We collect your email address in order to:</b></p>\n" +
                "\n" +
                "<p><b>To be in accordance with CANSPAM we agree to the following:</b></p>\n" +
                "\n" +
                "<p><b>If at any time you would like to unsubscribe from receiving future emails, you can email us at</b><br />\n" +
                "and we will promptly remove you from ALL correspondence.</p>\n" +
                "\n" +
                "<p><b>Contacting Us</b><br />\n" +
                " <br />\n" +
                "If there are any questions regarding this privacy policy you may contact us using the information below.<br />\n" +
                "<u><font color=blue>house1.in</font></u><br />\n" +
                "pruthvi residency<br />\n" +
                "hyderbad, hyderbad 500081<br />\n" +
                "india<br />\n" +
                "</p>" ;
        textView4.setText(Html.fromHtml(styledText4), TextView.BufferType.SPANNABLE);

        //Spanned spanned=(Html).fromHtml(styledText),TextView.Bu
        return  rootView;
    }
}
