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
public class HomeFragment5 extends Fragment {
    TextView textView;
    public HomeFragment5() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.homefragment5, container, false);
        textView=(TextView)rootView.findViewById(R.id.pera2);

        String styledText2 = "<html><body style=\"text-align:justify\"> <p>Welcome to <u><font color=blue> house1.in</font></u> &ndash; a complete online app for house needs and founded in 2016 aimed to serve house needs like painting, pluming&#8230;etc all home services is always driven by an enthusiastic spirit of responsibility to inform our readers about the latest trends and techniques to use our app, Since inception, we have been known as an independent community by our readers because of high quality of information and not with the quantity of the information we present.<br /><br /><br />\n" +"Behind  <u><font color=blue> house1.in</font></u>, there is a company called QAProgrammer &ndash; a well known company in the testing industry. Today, QAProgrammer Is an an independent testing community, we have been well nourished by the gratitude of our valuable benefactors and contributors supporting us to produce wide information treasure of high-quality articles, resources and tools out there for everybody.<br /><br /><br />\n" +
                "QAProgrammer provides a solid platform for thousands of talented and hard-working professionals to share their new insight from their work in different areas. We always welcome creative people who come up with brilliant ideas and want to share their valuable industry experience with fellow readers. QAProgrammer has been powered by the reach of robust and modular social networking via Facebook, Twitter etc.<br />\n" +
                "We are dedicated to obsessively deliver current news and development useful for our respective readers especially Service Providers. The content published on QAProgrammmer are objective to present noteworthy tips, useful guidelines, excellent tutorials, helpful tools for utilization of all our house servics<br /><br /><br />\n" +
                "We thank to founders of  <u><font color=blue> house1.in</font></u> (Muralidhar and Gopi Raghavendra ) for this  <u><font color=blue> house1.in</font></u>.<br /><br />\n" +
                "We connect though a mail to <u><font color=blue> support@house1.in</font></u><br />\n" +
                " <br />\n" +
                "</p></body></html> ";
        // textView2.setText(styledText2);
        textView.setText(Html.fromHtml(styledText2), TextView.BufferType.SPANNABLE);

        return rootView;

    }
}