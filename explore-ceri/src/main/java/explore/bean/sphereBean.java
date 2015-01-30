/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package explore.bean;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import explore.helper.Sphere;
import explore.lazyviewbeans.SphereLazyView;

/**
 *
 * @author Milos
 */
@Named(value = "sphereBean")
@SessionScoped
public class sphereBean implements Serializable {
    
   @Inject
   SphereLazyView sphereListData;

   private Sphere current;
   private Integer index;
   private List<Sphere> spheres;
    
    
    public sphereBean() {
    }
    
    @PostConstruct
    public void init() {
        this.spheres = new ArrayList<>();
        Sphere tmp = new Sphere();
        tmp.setIndex(0);
        tmp.setName("Doumo roof");
        tmp.setHtmlObject("<iframe    src=\"https://maps.google.com/maps?layer=c&amp;panoid=EWYRuus-5ocAAAQINSiudw&amp;ie=UTF8&amp;source=embed&amp;output=svembed&amp;cbp=13%2C3.859199999999987%2C%2C0%2C0\"></iframe>");
        this.spheres.add(tmp);
        tmp = new Sphere();
        tmp.setIndex(1);
        tmp.setName("Piazza Duomo - Night");
        tmp.setHtmlObject("<iframe    src=\"https://maps.google.com/maps?layer=c&amp;panoid=uOOwgu1Q3xEAAAAGOzA4ew&amp;ie=UTF8&amp;source=embed&amp;output=svembed&amp;cbp=13%2C357.2814944533546%2C%2C0%2C-6.586130887294345\"></iframe>");
        this.spheres.add(tmp);
        tmp = new Sphere();
        tmp.setIndex(2);
        tmp.setName("Politecnico - Trifoglio");
        tmp.setHtmlObject("<iframe    src=\"https://maps.google.com/maps?layer=c&amp;panoid=e4HRugiEEyYAAAAGOzDFyA&amp;ie=UTF8&amp;source=embed&amp;output=svembed&amp;cbp=13%2C297.7575943846471%2C%2C0%2C-2.8752389754227465\"></iframe>");
        this.spheres.add(tmp);
        tmp = new Sphere();
        tmp.setIndex(3);
        tmp.setName("Politecnico - PoliPrint");
        tmp.setHtmlObject("<iframe    src=\"https://maps.google.com/maps?layer=c&amp;panoid=v3G7HiPScM0AAAQW-cB7_w&amp;ie=UTF8&amp;source=embed&amp;output=svembed&amp;cbp=13%2C249.55655%2C%2C0%2C0\"></iframe>");
        this.spheres.add(tmp);
        tmp = new Sphere();
        tmp.setIndex(4);
        tmp.setName("Politecnico - Architecture Building");
        tmp.setHtmlObject("<iframe    src=\"https://maps.google.com/maps?layer=c&amp;panoid=97ziMvhvwhkAAAQW-cSgug&amp;ie=UTF8&amp;source=embed&amp;output=svembed&amp;cbp=13%2C292.62822%2C%2C0%2C0\"></iframe>");
        this.spheres.add(tmp);
        tmp = new Sphere();
        tmp.setIndex(5);
        tmp.setName("Politecnico - Hall");
        tmp.setHtmlObject("<iframe    src=\"https://maps.google.com/maps?layer=c&amp;panoid=srnIZe9e8PUAAAAGOuuv_g&amp;ie=UTF8&amp;source=embed&amp;output=svembed&amp;cbp=13%2C248.16358800048494%2C%2C0%2C31.653093369498976\"></iframe>");
        this.spheres.add(tmp);
        tmp = new Sphere();
        tmp.setIndex(6);
        tmp.setName("Politecnico - Schoolyard");
        tmp.setHtmlObject("<iframe    src=\"https://maps.google.com/maps?layer=c&amp;panoid=AMPhCZLHVJsAAAAGOvryQA&amp;ie=UTF8&amp;source=embed&amp;output=svembed&amp;cbp=13%2C150.074%2C%2C0%2C0\"></iframe>");
        this.spheres.add(tmp);
        tmp = new Sphere();
        tmp.setIndex(7);
        tmp.setName("Politecnico - Back entrance");
        tmp.setHtmlObject("<iframe    src=\"https://maps.google.com/maps?layer=c&amp;panoid=H-7YOIBwd2sAAAQXIjU2Uw&amp;ie=UTF8&amp;source=embed&amp;output=svembed&amp;cbp=13%2C11.703599999999938%2C%2C0%2C0\"></iframe>");
        this.spheres.add(tmp);
        tmp = new Sphere();
        tmp.setIndex(8);
        tmp.setName("Veterinarian Faculty");
        tmp.setHtmlObject("<iframe    src=\"https://maps.google.com/maps?layer=c&amp;panoid=zX1PIsolm_4AAAQXBgahRg&amp;ie=UTF8&amp;source=embed&amp;output=svembed&amp;cbp=13%2C15.130800000000022%2C%2C0%2C0\"></iframe>");
        this.spheres.add(tmp);
        tmp = new Sphere();
        tmp.setIndex(9);
        tmp.setName("Politecnico - Main Piazza");
        tmp.setHtmlObject("<iframe    src=\"https://maps.google.com/maps?layer=c&amp;panoid=LQH8DfakZaoAAAQIt4T5kA&amp;ie=UTF8&amp;source=embed&amp;output=svembed&amp;cbp=13%2C33.569999999999936%2C%2C0%2C0\"></iframe>");
        this.spheres.add(tmp);
        tmp = new Sphere();
        tmp.setIndex(10);
        tmp.setName("Lambrate Station");
        tmp.setHtmlObject("<iframe    src=\"https://maps.google.com/maps?layer=c&amp;panoid=CIpGk_KAhkIAAAQINTEUQA&amp;ie=UTF8&amp;source=embed&amp;output=svembed&amp;cbp=13%2C25.459199999999953%2C%2C0%2C0\"></iframe>");
        this.spheres.add(tmp);
        tmp = new Sphere();
        tmp.setIndex(11);
        tmp.setName("Piazza Loreto");
        tmp.setHtmlObject("<iframe    src=\"https://maps.google.com/maps?layer=c&amp;panoid=n_W3tK6s7hkAAAAGOtDHCA&amp;ie=UTF8&amp;source=embed&amp;output=svembed&amp;cbp=13%2C132.14406197550176%2C%2C0%2C-5.121383925040902\"></iframe>");
        this.spheres.add(tmp);
        tmp = new Sphere();
        tmp.setIndex(12);
        tmp.setName("Church");
        tmp.setHtmlObject("<iframe    src=\"https://maps.google.com/maps?layer=c&amp;panoid=thmkNxzbiTMAAAAGOz5WVQ&amp;ie=UTF8&amp;source=embed&amp;output=svembed&amp;cbp=13%2C120.92519999999996%2C%2C0%2C0\"></iframe>");
        this.spheres.add(tmp);
        tmp = new Sphere();
        tmp.setIndex(13);
        tmp.setName("Corso Buenos Aires");
        tmp.setHtmlObject("<iframe    src=\"https://maps.google.com/maps?layer=c&amp;panoid=BVTSsl5Rk6IAAAQfCN4Vrg&amp;ie=UTF8&amp;source=embed&amp;output=svembed&amp;cbp=13%2C248.37959828032058%2C%2C0%2C7.603635076273946\"></iframe>");
        this.spheres.add(tmp);
        tmp = new Sphere();
        tmp.setIndex(14);
        tmp.setName("Lima Station");
        tmp.setHtmlObject("<iframe    src=\"https://maps.google.com/maps?layer=c&amp;panoid=6kACxTjflcAAAAQfCO1qVg&amp;ie=UTF8&amp;source=embed&amp;output=svembed&amp;cbp=13%2C8.662399999999991%2C%2C0%2C0\"></iframe>");
        this.spheres.add(tmp);
        tmp = new Sphere();
        tmp.setIndex(15);
        tmp.setName("Porta Venezia");
        tmp.setHtmlObject("<iframe    src=\"https://maps.google.com/maps?layer=c&amp;panoid=BcrqHG4fEmkAAAQYNN5JZg&amp;ie=UTF8&amp;source=embed&amp;output=svembed&amp;cbp=13%2C136.01535107834655%2C%2C0%2C4.501431097433965\"></iframe>");
        this.spheres.add(tmp);
        tmp = new Sphere();
        tmp.setIndex(16);
        tmp.setName("Giardini Publici");
        tmp.setHtmlObject("<iframe    src=\"https://maps.google.com/maps?layer=c&amp;panoid=jYWC063OAkNmaU1FheCAOg&amp;ie=UTF8&amp;source=embed&amp;output=svembed&amp;ll=45.473922%2C9.200657&amp;cbp=13%2C36.65799439876582%2C%2C0%2C-0.7903035612156515\"></iframe>");
        this.spheres.add(tmp);
        tmp = new Sphere();
        tmp.setIndex(17);
        tmp.setName("La Scala - Ticket Office");
        tmp.setHtmlObject("<iframe    src=\"https://maps.google.com/maps?layer=c&amp;panoid=NvGKU9qtGYUAAAQIt-1i5g&amp;ie=UTF8&amp;source=embed&amp;output=svembed&amp;cbp=13%2C286.99321369166825%2C%2C0%2C0\"></iframe>");
        this.spheres.add(tmp);
        tmp = new Sphere();
        tmp.setIndex(18);
        tmp.setName("Piazza Duomo - Night 2");
        tmp.setHtmlObject("<iframe    src=\"https://maps.google.com/maps?layer=c&amp;panoid=lbPEOeaMT6EAAAQY9uGs8A&amp;ie=UTF8&amp;source=embed&amp;output=svembed&amp;cbp=13%2C14.987600000000043%2C%2C0%2C0\"></iframe>");
        this.spheres.add(tmp);
        tmp = new Sphere();
        tmp.setIndex(19);
        tmp.setName("Piazza Scala");
        tmp.setHtmlObject("<iframe    src=\"https://maps.google.com/maps?layer=c&amp;panoid=AhhgVTpANJgAAAQJKfi9pw&amp;ie=UTF8&amp;source=embed&amp;output=svembed&amp;cbp=13%2C315.44399999999996%2C%2C0%2C0\"></iframe>");
        this.spheres.add(tmp);
        tmp = new Sphere();
        tmp.setIndex(20);
        tmp.setName("Piazza Fellippo Meda");
        tmp.setHtmlObject("<iframe    src=\"https://maps.google.com/maps?layer=c&amp;panoid=rZ8OpZwO1YQAAAQJORzRTA&amp;ie=UTF8&amp;source=embed&amp;output=svembed&amp;cbp=13%2C156.10168421052632%2C%2C0%2C0\"></iframe>");
        this.spheres.add(tmp);
        tmp = new Sphere();
        tmp.setIndex(21);
        tmp.setName("Piazza San Fedele");
        tmp.setHtmlObject("<iframe    src=\"https://maps.google.com/maps?layer=c&amp;panoid=F3CJG0ozKJEAAAAGO0qmDQ&amp;ie=UTF8&amp;source=embed&amp;output=svembed&amp;cbp=13%2C59.38959999999997%2C%2C0%2C0\"></iframe>");
        this.spheres.add(tmp);
        tmp = new Sphere();
        tmp.setIndex(22);
        tmp.setName("Galleria Vittorio Emanuele II - Night");
        tmp.setHtmlObject("<iframe    src=\"https://maps.google.com/maps?layer=c&amp;panoid=n0fbeuUcAz4AAAQIt8O81w&amp;ie=UTF8&amp;source=embed&amp;output=svembed&amp;cbp=13%2C12.12119999999993%2C%2C0%2C0\"></iframe>");
        this.spheres.add(tmp);
        tmp = new Sphere();
        tmp.setIndex(23);
        tmp.setName("Galleria Vittorio Emanuele II - Day");
        tmp.setHtmlObject("<iframe    src=\"https://maps.google.com/maps?layer=c&amp;panoid=RtLBb5Unk0gAAAQY_P1hXg&amp;ie=UTF8&amp;source=embed&amp;output=svembed&amp;cbp=13%2C51.97372939541577%2C%2C0%2C4.285071349374618\"></iframe>");
        this.spheres.add(tmp);
        tmp = new Sphere();
        tmp.setIndex(24);
        tmp.setName("Piazza Duomo - Day 2");
        tmp.setHtmlObject("<iframe    src=\"https://maps.google.com/maps?layer=c&amp;panoid=hwnJW4XiTLBwSdBY_lysJg&amp;ie=UTF8&amp;source=embed&amp;output=svembed&amp;ll=45.464071%2C9.189478&amp;cbp=13%2C92.84909508771932%2C%2C0%2C-4.25\"></iframe>");
        this.spheres.add(tmp);
        tmp = new Sphere();
        tmp.setIndex(25);
        tmp.setName("Duomo Roof 2");
        tmp.setHtmlObject("<iframe    src=\"https://maps.google.com/maps?layer=c&amp;panoid=Ve7pkXy5hxMAAAQINSiuag&amp;ie=UTF8&amp;source=embed&amp;output=svembed&amp;cbp=13%2C41.44319999999999%2C%2C0%2C0\"></iframe>");
        this.spheres.add(tmp);
        tmp = new Sphere();
        tmp.setIndex(26);
        tmp.setName("Corso Vittorio Emanuele II");
        tmp.setHtmlObject("<iframe    src=\"https://maps.google.com/maps?layer=c&amp;panoid=sx4-2nHSY9cAAAQYnMy05w&amp;ie=UTF8&amp;source=embed&amp;output=svembed&amp;cbp=13%2C310.07912%2C%2C0%2C0\"></iframe>");
        this.spheres.add(tmp);
        tmp = new Sphere();
        tmp.setIndex(27);
        tmp.setName("Piazza Pio XI");
        tmp.setHtmlObject("<iframe    src=\"https://maps.google.com/maps?layer=c&amp;panoid=TAYR-mZbyCsAAAGusipDIQ&amp;ie=UTF8&amp;source=embed&amp;output=svembed&amp;cbp=13%2C317.9178287719298%2C%2C0%2C0\"></iframe>");
        this.spheres.add(tmp);
        tmp = new Sphere();
        tmp.setIndex(28);
        tmp.setName("Navigli - Under the bridge");
        tmp.setHtmlObject("<iframe    src=\"https://maps.google.com/maps?layer=c&amp;panoid=RAQgxR7RaicAAAQINlFZQA&amp;ie=UTF8&amp;source=embed&amp;output=svembed&amp;cbp=13%2C32.4324940350877%2C%2C0%2C0\"></iframe>");
        this.spheres.add(tmp);
        tmp = new Sphere();
        tmp.setIndex(29);
        tmp.setName("Navigli - Hidden passage");
        tmp.setHtmlObject("<iframe    src=\"https://maps.google.com/maps?layer=c&amp;panoid=Tj9Apq5sowAAAAQfCMxaug&amp;ie=UTF8&amp;source=embed&amp;output=svembed&amp;cbp=13%2C290.8813532335963%2C%2C0%2C4.010130131401297\"></iframe>");
        this.spheres.add(tmp);
        this.index = 0;
        this.current = this.spheres.get(index);
        this.sphereListData.init();
        }

    
    public void nextSphere(){
        this.index++;
        this.index = this.index%this.spheres.size();
        this.current = this.spheres.get(this.index);
    }
    
    public void previousSphere(){
        this.index--;
        if(this.index == -1)
            this.index = this.spheres.size()-1;
        this.current = this.spheres.get(this.index);
    }
    
    
    
    
    public Sphere getCurrent() {
        return current;
    }

    public void setCurrent(Sphere current) {
        this.current = current;
    }

    public List<Sphere> getSpheres() {
        return spheres;
    }

    public void setSpheres(List<Sphere> spheres) {
        this.spheres = spheres;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

   
    
    
    
}
