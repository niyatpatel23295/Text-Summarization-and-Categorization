/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package saransh;

/**
 *
 * @author Shubham
 */
public class CatComputer {
    
    
    int score;
    String[] comp = { "graphics", "graphical", "representation", "vector",
				"functions", "variables", "plot", "vectors", "vector",
				"plotting", "arrows", "arrow", "pointers", "pointer",
				"comp.graphics", "routines", "routine", "Polygon", "Renderman",
				"image", "colors", "color", "bits", "bit", "R/G/B", "visible",
				"picture", "machbanding", "gradient", "resolution", "visible",
				"dark", "green", "white", "yellow", "contrast", "monitor",
				"mach-banding", "image", "image", "edge", "edges", "brighter",
				"darker", "side", "TTTDDD", "format", "DXF", "effects",
				"effect", "functions", "function", "volumetric", "depicting",
				"depict", "Video", "phase", "visualizations", "visualization",
				"visualize", "tool", "tools", "render", "rendering", "2D",
				"3D", "dimensions", "dimension","Applet","Application", "Abend", 
                                "Apache","ADA", "biff", "bit", "booting", "bootstraping", "bug",
                                "byte", "error", "C", "C++", "Cookie", "Session", "web", "browser",
                                "web browser", "object-oriented", "programming", "language", 
                                "computer", "packet", "D", "Daemon", "Unix", "operating", "System", 
                                "Linux", "process", "Ethernet", "networking", "network", 
                                "GNU", "Google", "search", "engine" ,"command", "Hotmail", "mail",
                                "server", "tomcat", "Java", "Python", "JSP", "Servlet", "Ruby", 
                                "R", "C#", "php", "JavaScript", "Netscape", "kerberos", "Security",
                                "authentication", "protocol", "windows", "programmers", "programmers",
                                "Linux", "Lisa", "Liveware", "Apple", "Minix", "DOS", "ftp", "http", 
                                "smtp", "https", "macintosh", "Oracle", "database", "sql", "mysql", 
                                "bigdata", "hadoop", "spark", "perl", "pine", "ping", "scripting", 
                                "interpreted", "radio", "button", "cryptography", "RSA", "encryption", 
                                "decryption", "Samba", "shareware", "Microsoft", "SIMON", "spam", "SPIM", 
                                "swing", "library", "viruses", "Tomcat", "troff", "Trojan", "Horse", "Tux", "Ubuntu", 
                                "Debian", "vi", "vim", "virus", "wiki", "Worm", "WYSIWYG", "tapeworms", 
                                "yahoo!", "yahoo", "zip", "data", "address", "algorithm", "IP", "Cisco", 
                                "Desktop", "download", "table", "allocation", "filesystem", "firewal", 
                                "conjuction", "gigabyte", "megabyte", "terabyte", "kilobyte", "kb", "mb", 
                                "gb", "tb", "FreeBSD", "Facebook", "hacker", "hardware", "software", "softwares", "hash", 
                                "home page", "html", "J#", "kernel", "keyboard", "link", "Mac", "OS", 
                                "malware", "md5", "monitor", "motherboard", "mouse", "Mozilla", "Firefox", 
                                "Modem", "NTFS", "open", "office", "source", "site", "Opera", "pc", "PGP", 
                                "PDF", "RAM", "ROM", "Recycle", "Bin", "Scan", "reboot", "spamming", "spyware", 
                                "SDK", "surfing", "version", "virtual", "vulnerabilty", "XML", "DTD" ,"Scema", 
                                "XSD", "XSLT", "W3C", "API", "APIs", "XHTML", "SVG", "Container", "WAP", 
                                "WML", "RSS", "return", "drive", "fetch", "query", "primary", "composite", 
                                "super", "foreign", "delete", "drop", "physical", "accumulato", "cahe", "CPU", 
                                "cd-rom", "cpu", "chip", "memory", "ALU", "INPUT", "OUTPUT", "Floppy", 
                                "flash", "mainframe", "Non-volatile", "Volatile", "code", "RAID", "BITMAP", 
                                "Configuration", "Directory", "Document", "dtp", "Embedded", "Exe", "folder", 
                                "GUI", "Icon", "Macro", "object", "copy", "paste","cut", "pixel", "port", "push", 
                                "pull", "stack", "lifo", "fifo", "queue", "scroll", "css", "stylesheet", 
                                "subdirectory", "unit", "objects"};
    
     
    
    int getCompScore(String word){
    
        for (String comp1 : comp) {
    
//            System.out.print(comp1 +" "+word);
            if(comp1.equalsIgnoreCase(word)){
                score= 1;
                break;
            }else{
            
                score= 0;
            }
        }
        return score;
    }
    
}
